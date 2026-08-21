package com.carbonbyte.sonfiestas.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.repository.FestivalRepository
import com.carbonbyte.sonfiestas.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: FestivalRepository,
    private val connectivityObserver: ConnectivityObserver,
) : ViewModel() {

    private val intents = MutableSharedFlow<EventsIntent>(replay = 1)

    val uiState: StateFlow<EventsUiState> = merge(
        repository.getAgenda().map { StateChange.DataLoaded(it) },
        connectivityObserver.observe().map { StateChange.ConnectivityChanged(it) },
        intents.flatMapLatest { intent ->
            handleIntent(intent)
        },
    ).scan(EventsUiState(isLoading = true)) { state, change ->
        reduce(state, change)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EventsUiState(isLoading = true),
    )

    init {
        viewModelScope.launch {
            delay(1500.milliseconds)
            onIntent(EventsIntent.DismissSplash)
        }
        onIntent(EventsIntent.Refresh)
    }

    fun onIntent(intent: EventsIntent) {
        viewModelScope.launch {
            intents.emit(intent)
        }
    }

    private fun handleIntent(intent: EventsIntent): Flow<StateChange> = when (intent) {
        is EventsIntent.Refresh -> {
            repository.refreshAgendaFlow("default")
                .map { StateChange.Loading(it) }
                .onStart { emit(StateChange.Loading(true)) }
        }
        is EventsIntent.ToggleFavorite -> {
            repository.toggleFavoriteFlow(intent.eventId)
                .map { StateChange.None }
        }
        is EventsIntent.DismissSplash -> {
            kotlinx.coroutines.flow.flowOf(StateChange.SplashDismissed)
        }
        is EventsIntent.ToggleFavoritesFilter -> {
            kotlinx.coroutines.flow.flowOf(StateChange.FavoritesFilterToggled)
        }
        is EventsIntent.SelectDay -> {
            kotlinx.coroutines.flow.flowOf(StateChange.DaySelected(intent.index))
        }
    }

    private fun reduce(state: EventsUiState, change: StateChange): EventsUiState = when (change) {
        is StateChange.DataLoaded -> {
            val days = change.events.map { it.date }.distinct().sorted()
            state.copy(
                events = change.events,
                days = days,
                isLoading = false,
            )
        }
        is StateChange.ConnectivityChanged -> state.copy(
            isOffline = change.status != ConnectivityObserver.Status.Available,
        )
        is StateChange.Loading -> state.copy(isLoading = change.isLoading)
        is StateChange.SplashDismissed -> state.copy(showSplash = false)
        is StateChange.FavoritesFilterToggled -> state.copy(isFilteredByFavorites = !state.isFilteredByFavorites)
        is StateChange.DaySelected -> state.copy(selectedDayIndex = change.index)
        is StateChange.None -> state
    }

    private sealed class StateChange {
        data class DataLoaded(val events: List<Event>) : StateChange()
        data class ConnectivityChanged(val status: ConnectivityObserver.Status) : StateChange()
        data class Loading(val isLoading: Boolean) : StateChange()
        object SplashDismissed : StateChange()
        object FavoritesFilterToggled : StateChange()
        data class DaySelected(val index: Int) : StateChange()
        object None : StateChange()
    }
}

// Extension helpers for the repository to return flows for actions
private fun FestivalRepository.refreshAgendaFlow(id: String): Flow<Boolean> = kotlinx.coroutines.flow.flow {
    refreshAgenda(id)
    emit(false)
}

private fun FestivalRepository.toggleFavoriteFlow(id: Int): Flow<Unit> = kotlinx.coroutines.flow.flow {
    toggleFavorite(id)
    emit(Unit)
}
