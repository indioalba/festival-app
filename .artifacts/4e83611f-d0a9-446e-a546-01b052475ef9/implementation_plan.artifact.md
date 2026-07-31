# Implementation Plan - Create HTTP API Call (Issue #1)

This plan covers the implementation of an HTTP API call to retrieve the festival agenda, as requested in [Issue #1](https://github.com/indioalba/festival-app/issues/1).

## User Review Required

> [!IMPORTANT]
> **Base URL:** The issue specifies the URL as "TBD". I will use `https://api.festival.com/` as a placeholder. Please let me know if there is a specific URL I should use.

> [!NOTE]
> **Room & Hilt:** The `Event` class uses `@PrimaryKey`, and `AGENTS.md` mentions Hilt and Room. I will add these dependencies to the project as they are currently missing.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///Users/indioalba/Workspace/Festival/gradle/libs.versions.toml)
Add versions and libraries for:
- Retrofit & OkHttp
- Kotlin Serialization
- Room
- Hilt

#### [MODIFY] [build.gradle.kts (project)](file:///Users/indioalba/Workspace/Festival/build.gradle.kts)
Add Hilt and Kotlin Serialization plugins.

#### [MODIFY] [build.gradle.kts (:app)](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
Apply plugins and add dependencies for Retrofit, Room, and Hilt.

### Data Layer

#### [NEW] [Event.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/model/Event.kt)
Define the `Event` data class as specified in the issue.

#### [NEW] [FestivalApi.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/data/remote/FestivalApi.kt)
Define the Retrofit interface with the GET `/agenda` endpoint.

#### [NEW] [NetworkModule.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/di/NetworkModule.kt)
Hilt module to provide Retrofit and the API service.

### Infrastructure

#### [NEW] [FestivalApplication.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/indioalba/festival/FestivalApplication.kt)
Hilt Application class.

## Verification Plan

### Automated Tests
- Create a unit test for `FestivalApi` using `MockWebServer` to verify the request and response parsing.
- Run `./gradlew test` to verify.

### Manual Verification
- Verify the project builds successfully with new dependencies.
