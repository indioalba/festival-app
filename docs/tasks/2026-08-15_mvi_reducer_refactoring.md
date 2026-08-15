# Task: MVI Reducer Refactoring

Refactored the `EventsViewModel` to use a reducer-based MVI pattern for explicit state transitions.

## Date
2026-08-15 08:45

## Description
- Introduced `StateChange` sealed class for partial state updates.
- Implemented `reduce` function to handle state transitions.
- Used `merge` and `scan` operators to manage the state stream.
- Updated unit tests to support the new reactive state flow.

## Files Touched
- `app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt`
- `app/src/test/java/com/indioalba/festival/ui/events/EventsViewModelTest.kt`
