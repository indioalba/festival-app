# Apply MVI Presentation Pattern

This plan refactors the event list logic to follow the MVI (Model-View-Intent) pattern as specified in `AGENTS.md`.

## User Review Required

> [!NOTE]
> I will implement a standard MVI pattern using a single `UiState` stream and an `onIntent` function in the `ViewModel`.

## Proposed Changes

#### [MODIFY] [EventDao.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/local/EventDao.kt)
- Add `getEvent(id: Int): Flow<Event?>`.
- Add `toggleFavorite(id: Int)`.

#### [MODIFY] [FestivalRepository.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/repository/FestivalRepository.kt)
- Add `getEvent(id: Int): Flow<Event?>`.
- Add `toggleFavorite(id: Int)`.

#### [MODIFY] [OfflineFirstFestivalRepository.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/repository/OfflineFirstFestivalRepository.kt)
- Implement `getEvent` and `toggleFavorite` by delegating to the DAO.

---

### Presentation Layer

#### [NEW] [EventsUiState.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsUiState.kt)
- Define the state of the events screen:
    ```kotlin
    data class EventsUiState(
        val events: List<Event> = emptyList(),
        val isLoading: Boolean = false,
        val isOffline: Boolean = false
    )
    ```

#### [NEW] [EventsIntent.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsIntent.kt)
- Define user actions:
    ```kotlin
    sealed class EventsIntent {
        object Refresh : EventsIntent()
        data class ToggleFavorite(val eventId: Int) : EventsIntent()
    }
    ```

#### [NEW] [EventsViewModel.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt)
- Implement `EventsViewModel` using Hilt.
- Expose `uiState` as a `StateFlow`.
- Handle `EventsIntent` to trigger repository actions.

### UI Layer

#### [MODIFY] [MainActivity.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/MainActivity.kt)
- Remove direct repository injection.
- Use `EventsViewModel` to observe state and send intents.
- Update UI components to react to `EventsUiState`.

## Verification Plan

### Automated Tests
- Create `EventsViewModelTest` to verify that intents update the state correctly.
- Ensure all existing tests pass with `./gradlew testDemoDebugUnitTest`.

### Manual Verification
- Run the app and verify the list is still displayed correctly.
- Verify the loading state (if applicable) and offline indicator.
