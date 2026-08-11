# Walkthrough - Fixing CI Failures & Improving Observability

I have resolved several issues causing GitHub Actions to fail and added observability for screenshot tests.

## Changes Made

### 1. Improved CI Observability for Screenshots
Added a step to `Build.yaml` to upload Roborazzi screenshots and reports as GitHub Action artifacts. This will help in diagnosing why screenshot tests fail in the CI environment, especially since baseline images are not stored in the repository.

### 2. Resolved Gradle Configuration Conflict
Removed a manual task registration in the root `build.gradle.kts` that caused a `DuplicateTaskException`. The `lintProdRelease` task is automatically provided by the Android Gradle Plugin.

### 3. Fixed Instrumented Test Assertion
Updated `ExampleInstrumentedTest.kt` to correctly assert the package name when running with the `demo` flavor.

### 4. Updated Reviewer Trigger Logic
Changed the Reviewer subagent's trigger in `AGENTS.md` from a 35-minute interval to "every time there is a new commit on an open pull request" to speed up the feedback loop.

### 5. Resolved Spotless Formatting Issues
Fixed a formatting error in `build.gradle.kts` (consecutive comments) that was causing the `spotlessCheck` task to fail.

## Verification Results

### Local Build Success
All tasks executed by the GitHub Actions `Build.yaml` were verified locally:
- `spotlessCheck`: Passed
- `:app:dependencyGuard`: Passed
- `graphUpdate`: Passed
- `:app:verifyRoborazziDemoDebug`: Passed (locally, with baselines generated)
- `:app:testDemoDebugUnitTest`: Passed
- `:app:assembleDemoDebug`: Passed
- `checkProdReleaseBadging`: Passed

render_diffs(file:///Users/indioalba/Workspace/Festival/.github/workflows/Build.yaml)
render_diffs(file:///Users/indioalba/Workspace/Festival/build.gradle.kts)
render_diffs(file:///Users/indioalba/Workspace/Festival/app/src/androidTest/java/com/indioalba/festival/ExampleInstrumentedTest.kt)
render_diffs(file:///Users/indioalba/Workspace/Festival/.agent/AGENTS.md)
