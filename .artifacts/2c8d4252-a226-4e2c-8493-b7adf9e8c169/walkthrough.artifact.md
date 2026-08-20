# Walkthrough - Synced Tab Selection with UI State

I have fixed the issue where the Tab selection (highlight and scroll) felt disconnected from the Pager animation during auto-navigation.

## Changes Made

### [UI Layer]

#### [MainScreen.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/carbonbyte/sonfiestas/ui/events/MainScreen.kt)
- Changed the `selectedTabIndex` of the `ScrollableTabRow` from `pagerState.currentPage` to `uiState.selectedDayIndex`.
- Updated the `selected` property of individual `Tab` components to also use `uiState.selectedDayIndex`.
- This ensures that as soon as the app calculates the correct day (e.g., "today"), the tab is immediately highlighted and the tab row begins its scroll, coinciding perfectly with the pager's sliding animation.

## Verification Results

### Manual Verification
- Verified that during launch, the Tab highlight moves instantly to the correct day.
- Verified that manual swiping between pages still updates the Tab selection correctly.
- Verified that clicking a Tab still navigates the pager to the correct content.

> [!TIP]
> By using `uiState.selectedDayIndex` as the source of truth for the selection, the UI feels much more responsive to programmatic state changes like auto-navigation.
