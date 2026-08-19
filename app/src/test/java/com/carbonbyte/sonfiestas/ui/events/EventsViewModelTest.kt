package com.carbonbyte.sonfiestas.ui.events

import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.repository.FestivalRepository
import com.carbonbyte.sonfiestas.util.ConnectivityObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

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
    fun `initial state is loading and showing splash`() = runTest {
        val uiState = viewModel.uiState.value
        assertTrue(uiState.isLoading)
        assertTrue(uiState.showSplash)
    }

    @Test
    fun `Refresh intent triggers repository refresh`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        viewModel.onIntent(EventsIntent.Refresh)
        advanceUntilIdle()
        assertTrue(repository.refreshCalled)
    }

    @Test
    fun `ToggleFavorite intent triggers repository toggle`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        viewModel.onIntent(EventsIntent.ToggleFavorite(1))
        advanceUntilIdle()
        assertEquals(1, repository.toggledId)
    }

    @Test
    fun `state reflects connectivity changes`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()

        connectivityObserver.status.value = ConnectivityObserver.Status.Lost
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.isOffline)

        connectivityObserver.status.value = ConnectivityObserver.Status.Available
        advanceUntilIdle()
        assertFalse(viewModel.uiState.value.isOffline)
    }

    @Test
    fun `Splash is dismissed after delay`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        // Initially splash is shown
        assertTrue(viewModel.uiState.value.showSplash)
        
        advanceTimeBy(3001.milliseconds)
        advanceUntilIdle()
        assertFalse(viewModel.uiState.value.showSplash)
    }

    @Test
    fun `SelectDay intent updates selected index`() = runTest {
        backgroundScope.launch { viewModel.uiState.collect {} }
        advanceUntilIdle()
        
        viewModel.onIntent(EventsIntent.SelectDay(2))
        advanceUntilIdle()
        
        assertEquals(2, viewModel.uiState.value.selectedDayIndex)
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
