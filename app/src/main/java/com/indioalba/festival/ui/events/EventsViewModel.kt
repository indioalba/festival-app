package com.indioalba.festival.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.repository.FestivalRepository
import com.indioalba.festival.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: FestivalRepository,
    private val connectivityObserver: ConnectivityObserver,
) : ViewModel() {

    private val intents = MutableSharedFlow<EventsIntent>()

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
                .map { StateChange.None } // Toggling favorite updates the DB, which triggers repository.getAgenda()
        }
    }

    private fun reduce(state: EventsUiState, change: StateChange): EventsUiState = when (change) {
        is StateChange.DataLoaded -> state.copy(events = change.events, isLoading = false)
        is StateChange.ConnectivityChanged -> state.copy(
            isOffline = change.status != ConnectivityObserver.Status.Available,
        )
        is StateChange.Loading -> state.copy(isLoading = change.isLoading)
        is StateChange.None -> state
    }

    private sealed class StateChange {
        data class DataLoaded(val events: List<Event>) : StateChange()
        data class ConnectivityChanged(val status: ConnectivityObserver.Status) : StateChange()
        data class Loading(val isLoading: Boolean) : StateChange()
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
