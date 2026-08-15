package com.indioalba.festival.ui.events

import com.indioalba.festival.data.model.Event

data class EventsUiState(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val isOffline: Boolean = false
)
