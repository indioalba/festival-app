# Walkthrough - Configured Release Signing for Play Store

I have configured the project to support release signing for the Play Store. The setup uses `local.properties` to store sensitive information securely without committing it to Git.

## Changes Made

### [Build Configuration]

#### [MODIFY] [app/build.gradle.kts](file:///Users/indioalba/Workspace/Festival/app/build.gradle.kts)
- Added logic to load properties from `local.properties`.
- Defined a `release` signing configuration.
- Linked the `release` build type to use this signing configuration.

### [Local Configuration]

#### [MODIFY] [local.properties](file:///Users/indioalba/Workspace/Festival/local.properties)
- Added the following keys for you to fill:
    - `RELEASE_STORE_FILE`: Set to `../festival-release-key.jks` (pointing to the file you created).
    - `RELEASE_STORE_PASSWORD`: **Action Required** (Leave empty or fill).
    - `RELEASE_KEY_ALIAS`: Set to `festival-alias`.
    - `RELEASE_KEY_PASSWORD`: **Action Required** (Leave empty or fill).

## Final Steps for You

To generate the bundle, please follow these steps:

1.  **Fill Passwords**: Open your [local.properties](file:///Users/indioalba/Workspace/Festival/local.properties) file and fill in the passwords you used when creating the keystore:
    - `RELEASE_STORE_PASSWORD=your_keystore_password`
    - `RELEASE_KEY_PASSWORD=your_key_password`
2.  **Build the Bundle**: Run the following command in your terminal:
    ```bash
    ./gradlew :app:bundleProdRelease
    ```
3.  **Locate the File**: Once the build finishes, your bundle will be available at:
    `app/build/outputs/bundle/prodRelease/app-prod-release.aab`

> [!CAUTION]
> Never share your `local.properties` file or the `festival-release-key.jks` file. Keep them in a safe place (like a password manager or secure backup).
