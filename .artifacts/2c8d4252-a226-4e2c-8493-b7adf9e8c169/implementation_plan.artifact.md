# Implementation Plan - Generate Keystore and Create App Bundle

Since you don't have a keystore, we will generate a new one, configure the project to use it securely, and then build the Android App Bundle (.aab).

## Proposed Changes

### [Security & Configuration]

#### 1. Generate the Keystore
You will need to run a command in your terminal to create the keystore file.
> [!CAUTION]
> **Store this file and its passwords securely.** If you lose them, you will not be able to update your app on the Play Store in the future.

#### [MODIFY] [local.properties](file:///Users/indioalba/Workspace/Festival/local.properties)
- Add entries for the keystore path and credentials. This file is git-ignored, so your secrets won't be leaked.

### [Build Configuration]

#### [MODIFY] [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
- Configure `signingConfigs` to read values from `local.properties`.
- Assign the `release` signing config to the `prod` flavor's release build.

## Verification Plan

### Manual Verification
1. Run the `keytool` command (provided below).
2. Run `./gradlew :app:bundleProdRelease`.
3. Locate the bundle at `app/build/outputs/bundle/prodRelease/app-prod-release.aab`.
