# Use Reducer in MVI Pattern

This plan refactors the `EventsViewModel` to use a dedicated `reducer` function for state transitions, making the MVI implementation more explicit and easier to test.

## User Review Required

> [!NOTE]
> The refactoring will introduce a `reduce` function and use the `scan` operator or a state-updating mechanism to manage transitions. This follows a more "standard" MVI architecture.

## Proposed Changes

### Presentation Layer

#### [MODIFY] [EventsViewModel.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt)
- Introduce a `Result` or `StateChange` sealed class to represent partial updates to the state.
- Implement a `reducer(previousState: EventsUiState, change: StateChange): EventsUiState` function.
- Merge external data flows (Repository, Connectivity) into a single stream of `StateChange`s.
- Use the `scan` operator to maintain and update the state.

### Verification Plan

### Automated Tests
- Update `EventsViewModelTest` to ensure that state transitions occur correctly through the reducer.

### Manual Verification
- Verify the app's functionality remains unchanged (list loading, favorite toggling, offline banner).
