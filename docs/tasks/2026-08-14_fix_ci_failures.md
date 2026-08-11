# Task: Fix CI Failures

Resolved the issues causing GitHub Actions and local Gradle sync to fail.

## Date
2026-08-14 13:48

## Description
- Removed redundant `lintProdRelease` task registration in root `build.gradle.kts` to resolve `DuplicateTaskException`.
- Updated `ExampleInstrumentedTest.kt` to correctly assert the package name for the `demo` flavor.
- Updated `.gitignore` to exclude `.idea/deploymentTargetSelector.xml` and removed it from version control.

## Files Touched
- `build.gradle.kts`
- `app/src/androidTest/java/com/indioalba/festival/ExampleInstrumentedTest.kt`
- `.gitignore`
