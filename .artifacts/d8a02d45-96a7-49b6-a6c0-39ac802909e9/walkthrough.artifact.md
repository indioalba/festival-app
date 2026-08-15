# Walkthrough - Database Seeder & Event List Display

I have implemented the `DatabaseSeeder` to provide initial data and updated the UI to display a list of events.

## Changes Made

### 1. Data Layer Enhancements
- **EventCategory Enum**: Defined `EventCategory` to categorize events (MUSIC, BULLS, SPORTS, etc.).
- **DAO Updates**: Added `getAnyEvent()` to check if the database is empty and `insertAll()` for bulk inserts in `EventDao`.
- **Database Seeder**: Created `DatabaseSeeder` with a comprehensive list of initial events to populate the database if it's empty.
- **Repository Integration**: Updated `OfflineFirstFestivalRepository` to use the `DatabaseSeeder` as a fallback mechanism, ensuring data is available even without a network connection.

### 2. MVI Presentation Pattern Implementation
Refactored the UI logic to follow the MVI (Model-View-Intent) pattern for better state management and unidirectional data flow.
- **EventsUiState**: A single source of truth for the screen state (events, loading, offline status).
- **EventsIntent**: Defined user actions like `Refresh` and `ToggleFavorite`.
- **EventsViewModel**: Handles intents, manages state, and interacts with the repository.
- **MainActivity**: Observes `uiState` and propagates user actions as intents.
- **UI Enhancements**: Added an interactive "Favorite" icon to each event item.

## Verification Results

### Automated Tests
- **ViewModel Tests**: `EventsViewModelTest` verified intent handling and state updates.
- **Unit Tests**: `OfflineFirstFestivalRepositoryTest` passed successfully.
- **Gradle Build**: Verified that the project builds correctly with `:app:assembleDemoDebug`.

### Manual Verification
- The app now displays a list of events on start.
- The "Offline Mode" banner appears correctly when connectivity is lost.

render_diffs(file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/local/DatabaseSeeder.kt)
render_diffs(file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/MainActivity.kt)
