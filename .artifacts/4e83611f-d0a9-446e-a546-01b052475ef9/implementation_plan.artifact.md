# Implementation Plan - Satisfying CI Quality Gates (Build.yaml)

This plan outlines the steps required to make the existing `Build.yaml` workflow pass by adding missing quality tools and configurations to the Festival project.

## User Review Required

> [!IMPORTANT]
> **Complex CI Pipeline:** The current `Build.yaml` is configured for a much more complex project structure (similar to Google's "Now In Android"). Implementing all these gates will add significant infrastructure code to your project.

> [!NOTE]
> **Screenshots:** Roborazzi requires baseline images. After setup, the first CI run might still "fail" if it expects pre-existing screenshots to compare against.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///Users/indioalba/Workspace/Festival/gradle/libs.versions.toml)
Add versions and library/plugin aliases for:
- Spotless (`com.diffplug.spotless`)
- Dependency Guard (`com.dropbox.dependency-guard`)
- Roborazzi (`io.github.takahirom.roborazzi`)
- Module Graph Assertion (`com.jraska.module.graph.assertion`)

#### [MODIFY] [build.gradle.kts (root)](file:///Users/indioalba/Workspace/Festival/build.gradle.kts)
Apply and configure global plugins:
- Spotless (formatting rules for `*.kt` and `*.gradle.kts`)
- Module Graph (to enable `graphUpdate`)

#### [MODIFY] [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
Apply and configure module-specific plugins:
- Dependency Guard
- Roborazzi (include Robolectric dependencies)

### Infrastructure

#### [NEW] [spotless/copyright.kt](file:///Users/indioalba/Workspace/Festival/spotless/copyright.kt)
Placeholder for copyright header if required by spotless config.

#### [NEW] [app/dependencies/debug.txt](file:///Users/indioalba/Workspace/Festival/app/dependencies/debug.txt)
Initial baseline for Dependency Guard.

## Verification Plan

### Automated Tests
1. Run `./gradlew spotlessCheck` to verify formatting.
2. Run `./gradlew dependencyGuard` to verify dependency tracking.
3. Run `./gradlew verifyRoborazziDemoDebug` (will likely require generating baselines first via `recordRoborazziDemoDebug`).
4. Run `./gradlew graphUpdate` to verify graph generation.

### Manual Verification
- Push a test commit to verify that GitHub Actions successfully runs all jobs in `Build.yaml`.
