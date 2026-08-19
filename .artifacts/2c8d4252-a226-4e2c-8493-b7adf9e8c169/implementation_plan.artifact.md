# Implementation Plan - Fix Application ID for Play Store Upload

The Google Play Store rejected the App Bundle because its application ID was `com.carbonbyte.sonfiestas.prod` instead of the expected `com.carbonbyte.sonfiestas`. This occurred because the `prod` flavor in `build.gradle.kts` had an `applicationIdSuffix`.

## Proposed Changes

### [Build Configuration]

#### [MODIFY] [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
- Remove `applicationIdSuffix = ".prod"` from the `prod` flavor.
- Remove `versionNameSuffix = "-prod"` from the `prod` flavor to keep the version name clean for production.

## Verification Plan

### Manual Verification
1. Run `./gradlew :app:bundleProdRelease`.
2. Verify that the generated bundle's application ID is `com.carbonbyte.sonfiestas`. (You can do this by attempting the upload again or using `aapt2 dump badging`).
