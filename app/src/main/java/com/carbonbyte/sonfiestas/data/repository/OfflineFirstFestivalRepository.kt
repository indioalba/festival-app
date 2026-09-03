package com.carbonbyte.sonfiestas.data.repository

import com.carbonbyte.sonfiestas.data.local.DatabaseSeeder
import com.carbonbyte.sonfiestas.data.local.EventDao
import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.remote.FestivalApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstFestivalRepository @Inject constructor(
    private val festivalApi: FestivalApi,
    private val eventDao: EventDao,
) : FestivalRepository {
    override fun getAgenda(): Flow<List<Event>> = eventDao.getAllEvents()

    override fun getEvent(id: Int): Flow<Event?> = eventDao.getEvent(id)

    override suspend fun toggleFavorite(id: Int) {
        withContext(Dispatchers.IO) {
            eventDao.toggleFavorite(id)
        }
    }

    override suspend fun refreshAgenda(festivalId: String) {
        withContext(Dispatchers.IO) {
            try {
                // Pre-seed if empty to have something while fetching
                DatabaseSeeder.seedIfEmpty(eventDao)

                val events = festivalApi.getAgenda(festivalId)
                if (events.isNotEmpty()) {
                    // Preserve favorites based on title, date and time
                    val currentFavorites = eventDao.getAllEventsSync()
                        .filter { it.isFavorite }
                        .map { "${it.title}|${it.date}|${it.time}" }
                        .toSet()

                    val updatedEvents = events.map { event ->
                        val key = "${event.title}|${event.date}|${event.time}"
                        if (currentFavorites.contains(key)) {
                            event.copy(isFavorite = true)
                        } else {
                            event
                        }
                    }

                    eventDao.refreshEvents(updatedEvents)
                }
            } catch (e: Exception) {
                // Ensure we have at least seed data if anything fails
                DatabaseSeeder.seedIfEmpty(eventDao)
                e.printStackTrace()
            }
        }
    }
}
