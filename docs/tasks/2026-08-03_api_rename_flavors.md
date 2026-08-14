# Task: Refactor API Naming and Add Flavors

**Date:** 2026-08-03
**Time:** 16:45 (approx)

## Description
Refactored the API interface naming and added project flavors. 
- Renamed `FestivalApi` (which was briefly `festivalApi`) for better consistency.
- Added `demo` and `prod` product flavors to `app/build.gradle.kts`.
- Updated `AGENTS.md` to change the reviewer interval to 35 minutes.
- Fixed naming convention and flavor mismatch issues identified by the automated Reviewer.

## Files Touched
- `.agent/AGENTS.md`
- `app/build.gradle.kts`
- `app/src/main/java/com/indioalba/festival/data/remote/FestivalApi.kt`
- `app/src/main/java/com/indioalba/festival/di/NetworkModule.kt`

## Verification
- Automated Reviewer confirmed best practices and project requirements.
- PR #3 was successfully merged into `main`.
