# Walkthrough - Fixed Application ID for Play Store

I have fixed the issue where the `prod` flavor was incorrectly appending a suffix to the application ID, which caused the Play Store to reject the upload.

## Changes Made

### [Build Configuration]

#### [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
- Removed `applicationIdSuffix = ".prod"` from the `prod` product flavor.
- Removed `versionNameSuffix = "-prod"` from the `prod` product flavor.

> [!NOTE]
> This ensures that when you build the `prod` version, the application ID remains exactly `com.carbonbyte.sonfiestas`, as registered in the Google Play Console.

## Verification Results

### Manual Verification
- You should now be able to run `./gradlew :app:bundleProdRelease` and upload the resulting `.aab` file without the "incorrect package name" error.
