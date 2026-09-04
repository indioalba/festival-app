package com.carbonbyte.sonfiestas.ui.events

import com.carbonbyte.sonfiestas.data.model.Event

data class EventsUiState(
    val showSplash: Boolean = true,
    val events: List<Event> = emptyList(),
    val days: List<String> = emptyList(),
    val selectedDayIndex: Int = 0,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val isFilteredByFavorites: Boolean = false,
)
