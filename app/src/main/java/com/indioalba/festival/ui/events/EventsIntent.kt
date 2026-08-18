package com.indioalba.festival.ui.events

sealed class EventsIntent {
    object Refresh : EventsIntent()
    data class ToggleFavorite(val eventId: Int) : EventsIntent()
    object DismissSplash : EventsIntent()
    object ToggleFavoritesFilter : EventsIntent()
    data class SelectDay(val index: Int) : EventsIntent()
}
