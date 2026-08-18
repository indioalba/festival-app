package com.indioalba.festival.ui.events

import com.indioalba.festival.data.model.Event

data class EventsUiState(
    val showSplash: Boolean = true,
    val events: List<Event> = emptyList(),
    val days: List<String> = emptyList(),
    val selectedDayIndex: Int = 0,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val isFilteredByFavorites: Boolean = false
) {
    val filteredEvents: List<Event>
        get() {
            val selectedDay = days.getOrNull(selectedDayIndex) ?: return emptyList()
            return events.filter { it.date == selectedDay }
                .filter { if (isFilteredByFavorites) it.isFavorite else true }
        }
}
