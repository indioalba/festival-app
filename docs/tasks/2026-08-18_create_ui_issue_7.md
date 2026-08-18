# Task: Create UI (Issue #7)

Implemented a comprehensive UI overhaul based on the provided requirements and design templates.

## Date
2026-08-18 12:50

## Description
- **Splash Screen**: Implemented a 3-second splash screen using the provided community image URL.
- **Main Screen Layout**:
    - Added a `CenterAlignedTopAppBar` with the title "Fiestas Agosto '26".
    - Integrated a "Favorite" filter in the toolbar to toggle between all events and only favorites.
    - Implemented a `TabRow` and `HorizontalPager` for navigating between different festival days.
- **Event Item UI**:
    - Refined the `EventItem` to include:
        - Category-specific icons (Music, Sports, Bulls, etc.).
        - Event time and title with hierarchical typography.
        - Location details with a pin icon.
        - Network image loading via **Coil**.
        - Interactive favorite heart toggle.
- **Infrastructure**:
    - Added `coil-compose` dependency.
    - Fixed date formatting logic for the Spanish locale.

## Files Touched
- `gradle/libs.versions.toml`
- `app/build.gradle.kts`
- `app/src/main/java/com/indioalba/festival/MainActivity.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/SplashScreen.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsUiState.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsIntent.kt`
- `app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt`
- `app/src/test/java/com/indioalba/festival/ui/events/EventsViewModelTest.kt`
