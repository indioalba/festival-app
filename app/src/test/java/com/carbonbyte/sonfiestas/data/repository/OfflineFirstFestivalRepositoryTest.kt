package com.carbonbyte.sonfiestas.data.repository

import com.carbonbyte.sonfiestas.data.local.EventDao
import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.remote.FestivalApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OfflineFirstFestivalRepositoryTest {

    private lateinit var repository: OfflineFirstFestivalRepository
    private lateinit var fakeApi: FakeFestivalApi
    private lateinit var fakeDao: FakeEventDao

    @Before
    fun setUp() {
        fakeApi = FakeFestivalApi()
        fakeDao = FakeEventDao()
        repository = OfflineFirstFestivalRepository(fakeApi, fakeDao)
    }

    @Test
    fun `refreshAgenda fetches from network and updates local database`() = runTest {
        val networkEvents = listOf(
            Event(id = 1, title = "Concert", date = "2026-08-15", time = "20:00", category = "Music"),
        )
        fakeApi.events = networkEvents

        repository.refreshAgenda("festival-123")

        val localEvents = repository.getAgenda().first()
        assertEquals(networkEvents, localEvents)
    }

    // Fakes
    class FakeFestivalApi : FestivalApi {
        var events = listOf<Event>()
        override suspend fun getAgenda(festivalId: String): List<Event> = events
    }

    class FakeEventDao : EventDao {
        private val dbEvents = MutableStateFlow<List<Event>>(emptyList())
        override fun getAllEvents(): Flow<List<Event>> = dbEvents

        override fun insertAll(events: List<Event>) {
            dbEvents.value = events
        }

        override fun getAnyEvent(): Event? {
            return dbEvents.value.firstOrNull()
        }

        override fun getEvent(id: Int): Flow<Event?> = flowOf(null)

        override fun toggleFavorite(id: Int) {
            // No-op for now
        }

        override fun deleteAllEvents() {
            dbEvents.value = emptyList()
        }
    }
}
