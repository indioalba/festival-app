# Walkthrough - Enabled GitHub Checks

I have fixed the configuration issues that were preventing GitHub Actions from running on your repository.

## Changes Made

### [GitHub Workflows]

#### [Build.yaml](file:///Users/indioalba/Workspace/Festival/.github/workflows/Build.yaml)
- Added a descriptive header comment. This small change acts as a "ping" to force GitHub to re-evaluate and trigger the workflow on your current Pull Request.

#### [Release.yml](file:///Users/indioalba/Workspace/Festival/.github/workflows/Release.yml)
- Corrected the repository filter. It was previously hardcoded to only run on `android/nowinandroid`. I've updated it to `indioalba/festival-app`.

#### [NightlyBaselineProfiles.yaml](file:///Users/indioalba/Workspace/Festival/.github/workflows/NightlyBaselineProfiles.yaml)
- Similarly corrected the repository filter from `android/nowinandroid` to `indioalba/festival-app`.

## Verification Results

### Manual Verification
- Once you push these changes to GitHub, the following 3 checks should automatically appear and start running on your PR:
  1. **Local tests and APKs** (Unit tests and builds)
  2. **Instrumented Tests (26)** (Android Emulator tests on API 26)
  3. **Instrumented Tests (34)** (Android Emulator tests on API 34)

> [!TIP]
> You can monitor the progress of these checks directly in the "Actions" tab of your GitHub repository or at the bottom of your Pull Request page.
