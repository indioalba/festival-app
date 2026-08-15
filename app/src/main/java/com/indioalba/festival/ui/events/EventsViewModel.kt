package com.indioalba.festival.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indioalba.festival.data.repository.FestivalRepository
import com.indioalba.festival.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: FestivalRepository,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val uiState: StateFlow<EventsUiState> = combine(
        repository.getAgenda(),
        connectivityObserver.observe(),
        _isLoading
    ) { events, connectivityStatus, isLoading ->
        EventsUiState(
            events = events,
            isLoading = isLoading,
            isOffline = connectivityStatus != ConnectivityObserver.Status.Available
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EventsUiState(isLoading = true)
    )

    init {
        onIntent(EventsIntent.Refresh)
    }

    fun onIntent(intent: EventsIntent) {
        when (intent) {
            is EventsIntent.Refresh -> refreshAgenda()
            is EventsIntent.ToggleFavorite -> toggleFavorite(intent.eventId)
        }
    }

    private fun refreshAgenda() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.refreshAgenda("default")
            _isLoading.value = false
        }
    }

    private fun toggleFavorite(eventId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(eventId)
        }
    }
}
