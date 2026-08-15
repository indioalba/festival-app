package com.indioalba.festival.ui.events

import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.repository.FestivalRepository
import com.indioalba.festival.util.ConnectivityObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EventsViewModelTest {

    private lateinit var viewModel: EventsViewModel
    private lateinit var repository: FakeFestivalRepository
    private lateinit var connectivityObserver: FakeConnectivityObserver
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeFestivalRepository()
        connectivityObserver = FakeConnectivityObserver()
        viewModel = EventsViewModel(repository, connectivityObserver)
    }

    @Test
    fun `initial state is loading`() = runTest {
        val uiState = viewModel.uiState.value
        assertTrue(uiState.isLoading)
    }

    @Test
    fun `Refresh intent triggers repository refresh`() = runTest {
        viewModel.onIntent(EventsIntent.Refresh)
        advanceUntilIdle()
        assertTrue(repository.refreshCalled)
    }

    @Test
    fun `ToggleFavorite intent triggers repository toggle`() = runTest {
        viewModel.onIntent(EventsIntent.ToggleFavorite(1))
        advanceUntilIdle()
        assertEquals(1, repository.toggledId)
    }

    @Test
    fun `state reflects connectivity changes`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        
        connectivityObserver.status.value = ConnectivityObserver.Status.Lost
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.isOffline)

        connectivityObserver.status.value = ConnectivityObserver.Status.Available
        advanceUntilIdle()
        assertFalse(viewModel.uiState.value.isOffline)
    }

    // Fakes
    class FakeFestivalRepository : FestivalRepository {
        var refreshCalled = false
        var toggledId: Int? = null
        private val events = MutableStateFlow<List<Event>>(emptyList())

        override fun getAgenda(): Flow<List<Event>> = events
        override fun getEvent(id: Int): Flow<Event?> = flowOf(null)
        override suspend fun toggleFavorite(id: Int) {
            toggledId = id
        }
        override suspend fun refreshAgenda(festivalId: String) {
            refreshCalled = true
        }
    }

    class FakeConnectivityObserver : ConnectivityObserver {
        val status = MutableStateFlow(ConnectivityObserver.Status.Available)
        override fun observe(): Flow<ConnectivityObserver.Status> = status
    }
}
