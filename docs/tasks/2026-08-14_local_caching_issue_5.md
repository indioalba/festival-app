# Task: Implement Local Caching for Events (Issue #5)

## Status
- [x] Room Setup (DAO and Database)
- [x] Repository Pattern (Offline-first implementation)
- [x] Dependency Injection (Hilt modules)
- [x] Offline Indicator in MainActivity
- [x] Unit Test for Repository
- [ ] Documentation (In progress)

## Changes
### Local Data
- Created `EventDao` with methods for observing events and updating the database.
- Created `FestivalDatabase`.

### Repository
- Created `FestivalRepository` interface.
- Implemented `OfflineFirstFestivalRepository` which syncs network data to Room.

### Dependency Injection
- Added `DatabaseModule` and `RepositoryModule` for Hilt.
- Updated `NetworkModule` to provide `ConnectivityObserver`.

### UI
- Added `ConnectivityObserver` and `NetworkConnectivityObserver`.
- Updated `MainActivity` with a red "Offline Mode" banner when connectivity is lost.

### Testing
- Added `OfflineFirstFestivalRepositoryTest` using fakes for API and DAO.

## Issues encountered
- KSP compilation error: `unexpected jvm signature V`.
  - **Resolution**: Removed `suspend` keyword from DAO methods (`upsertEvents` and `deleteAllEvents`). The repository now handles background execution using `withContext(Dispatchers.IO)`. This is a known workaround for signature conflicts in some KSP/Room environments.
