# Implementation Plan - Revert Auto-navigation to Current Date

The goal is to remove all code logic related to automatically selecting the current date upon app launch and revert to simple default behavior (starting at the first day).

## Proposed Changes

### [UI Layer]

#### [MODIFY] [EventsUiState.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/carbonbyte/sonfiestas/ui/events/EventsUiState.kt)
- Remove the `hasAutoNavigated` property.

#### [MODIFY] [EventsViewModel.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/carbonbyte/sonfiestas/ui/events/EventsViewModel.kt)
- Simplify the `reduce` function for `StateChange.DataLoaded`.
- Remove the date calculation logic (`SimpleDateFormat`, `Date()`, etc.).
- Ensure `selectedDayIndex` defaults to 0 on initial load and is preserved otherwise.
- Remove unused imports (`java.text.SimpleDateFormat`, `java.util.Date`, `java.util.Locale`).

#### [MODIFY] [MainScreen.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/carbonbyte/sonfiestas/ui/events/MainScreen.kt)
- Revert `ScrollableTabRow`'s `selectedTabIndex` back to `pagerState.currentPage`.
- Revert the `Tab`'s `selected` property to use `pagerState.currentPage == index`.
- (This keeps the UI selection tied to the Pager, which is the standard behavior the user had before the auto-nav syncing attempts).

## Verification Plan

### Manual Verification
- Open the app. It should always start on the first tab (index 0).
- Swipe the pager. Tabs should update accordingly.
- Click a tab. Pager should move accordingly.
