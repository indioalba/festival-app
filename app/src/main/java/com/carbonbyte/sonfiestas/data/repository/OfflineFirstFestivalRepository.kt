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
                DatabaseSeeder.seedIfEmpty(eventDao)
                val events = festivalApi.getAgenda(festivalId)
                if (events.isNotEmpty()) {
                    eventDao.deleteAllEvents()
                    eventDao.insertAll(events)
                }
            } catch (e: Exception) {
                // Fallback to seeder if network fails and DB is empty
                DatabaseSeeder.seedIfEmpty(eventDao)
                e.printStackTrace()
            }
        }
    }
}
