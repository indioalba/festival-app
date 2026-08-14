package com.indioalba.festival.data.repository

import com.indioalba.festival.data.local.EventDao
import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.remote.FestivalApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
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
            Event(id = 1, title = "Concert", date = "2026-08-15", time = "20:00", category = "Music")
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
        private val _events = MutableStateFlow<List<Event>>(emptyList())
        override fun getAllEvents(): Flow<List<Event>> = _events

        override fun upsertEvents(events: List<Event>) {
            _events.value = events
        }

        override fun deleteAllEvents() {
            _events.value = emptyList()
        }
    }
}
