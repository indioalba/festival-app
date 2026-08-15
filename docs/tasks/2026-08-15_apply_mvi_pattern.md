# Task: Apply MVI Presentation Pattern

Refactored the events screen to follow the MVI (Model-View-Intent) pattern as specified in `AGENTS.md`.

## Date
2026-08-15 08:35

## Description
- Created `EventsUiState` to hold the state of the events screen.
- Created `EventsIntent` to define user actions (`Refresh`, `ToggleFavorite`).
- Implemented `EventsViewModel` to handle intents and manage the UI state.
- Updated `MainActivity` to use the `EventsViewModel` and observe the `uiState`.
- Added a "Favorite" toggle feature to event items.
- Added `androidx-lifecycle-viewmodel-compose` and `androidx-compose-material-icons-extended` dependencies.

## Files Touched
- `gradle/libs.versions.toml`
- `app/build.gradle.kts`
- `app/src/main/java/com/indioalba/festival/MainActivity.kt`
- `app/src/main/java/com/indioalba/festival/data/repository/FestivalRepository.kt`
- `app/src/main/java/com/indioalba/festival/data/repository/OfflineFirstFestivalRepository.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsUiState.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsIntent.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt`
- `app/src/test/java/com/indioalba/festival/ui/events/EventsViewModelTest.kt`
- `app/src/test/java/com/indioalba/festival/data/repository/OfflineFirstFestivalRepositoryTest.kt`
