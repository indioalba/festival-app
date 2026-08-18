# [FR]: Create UI (Issue #7)

This plan implements a major UI overhaul including a splash screen, tabbed navigation with a horizontal pager, and a refined event list layout based on the Figma design.

## User Review Required

> [!IMPORTANT]
> - **Splash Screen**: I will implement the splash logic in `MainActivity` using a state to avoid creating multiple activities and keep the transition smooth.
> - **Date Formatting**: The tabs will display names like "Viernes 21". I will implement a helper to format the `date` string from the `Event` model.
> - **Icons**: I will use Material Design icons for categories (e.g., `Music`, `Sports`) since custom category icons are not provided.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///Users/indioalba/Workspace/Festival/gradle/libs.versions.toml)
- Add `coil-compose` for image loading.

#### [MODIFY] [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
- Add `coil-compose` dependency.

---

### Presentation Layer

#### [MODIFY] [EventsUiState.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsUiState.kt)
- Add `showSplash: Boolean`.
- Add `isFilteredByFavorites: Boolean`.
- Add `days: List<String>` (list of unique dates for tabs).
- Add `selectedDayIndex: Int`.

#### [MODIFY] [EventsIntent.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsIntent.kt)
- Add `DismissSplash`.
- Add `ToggleFavoritesFilter`.
- Add `SelectDay(index: Int)`.

#### [MODIFY] [EventsViewModel.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/EventsViewModel.kt)
- Update state logic to handle splash timeout (3 seconds).
- Implement grouping of events by day.
- Implement filtering by favorites within the selected day.
- Add logic to derive `days` from the event list.

---

### UI Layer

#### [NEW] [SplashScreen.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/ui/events/SplashScreen.kt)
- Display the splash image full-screen.

#### [MODIFY] [MainActivity.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/MainActivity.kt)
- Refactor the `setContent` block:
    - If `showSplash`, show `SplashScreen`.
    - Else, show the main UI:
        - `Scaffold` with a `CenterAlignedTopAppBar`.
        - `TabRow` for days.
        - `HorizontalPager` for the list of events per day.
- Update `EventItem` with:
    - Category icon.
    - Time and Title.
    - Location with pin icon.
    - Image loading with Coil (if `imageUrl` is present).
    - Favorite toggle icon.

## Verification Plan

### Automated Tests
- Update `EventsViewModelTest` to verify:
    - Splash state transition.
    - Day selection.
    - Favorites filtering.

### Manual Verification
- Verify the 3-second splash screen on app start.
- Verify that tabs correctly filter events by day.
- Verify horizontal swipe between days.
- Verify the "Favorite" filter in the toolbar.
