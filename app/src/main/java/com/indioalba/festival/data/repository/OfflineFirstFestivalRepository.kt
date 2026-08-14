package com.indioalba.festival.data.repository

import com.indioalba.festival.data.local.EventDao
import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.remote.FestivalApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstFestivalRepository @Inject constructor(
    private val festivalApi: FestivalApi,
    private val eventDao: EventDao,
) : FestivalRepository {
    override fun getAgenda(): Flow<List<Event>> = eventDao.getAllEvents()

    override suspend fun refreshAgenda(festivalId: String) {
        withContext(Dispatchers.IO) {
            try {
                val events = festivalApi.getAgenda(festivalId)
                eventDao.deleteAllEvents()
                eventDao.upsertEvents(events)
            } catch (e: Exception) {
                // Handle network errors or other issues
                e.printStackTrace()
            }
        }
    }
}
