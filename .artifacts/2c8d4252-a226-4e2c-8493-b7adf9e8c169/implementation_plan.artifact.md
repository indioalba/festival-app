# Implementation Plan - Fix and Enable GitHub Action Checks

The user reports that GitHub checks are missing. Investigation shows that two out of the three workflow files are explicitly disabled for this repository, and the primary build workflow might need a "ping" to trigger correctly on the current PR.

## User Review Required

> [!NOTE]
> Two workflows (`Release.yml` and `NightlyBaselineProfiles.yaml`) were configured to only run on the `android/nowinandroid` repository. I will update these to match your repository name (`indioalba/festival-app`).

## Proposed Changes

### [GitHub Workflows]

#### [MODIFY] [.github/workflows/Build.yaml](file:///Users/indioalba/Workspace/Festival/.github/workflows/Build.yaml)
- Add a minor comment to trigger a re-evaluation of the workflow by GitHub.
- Ensure the `pull_request` trigger is robust.

#### [MODIFY] [.github/workflows/Release.yml](file:///Users/indioalba/Workspace/Festival/.github/workflows/Release.yml)
- Update the `if: github.repository == 'android/nowinandroid'` condition to `if: github.repository == 'indioalba/festival-app'`.

#### [MODIFY] [.github/workflows/NightlyBaselineProfiles.yaml](file:///Users/indioalba/Workspace/Festival/.github/workflows/NightlyBaselineProfiles.yaml)
- Update the `if: github.repository == 'android/nowinandroid'` condition to `if: github.repository == 'indioalba/festival-app'`.

## Verification Plan

### Manual Verification
1. Push the changes to the `feature/issue-5-local-caching` branch.
2. Check the PR page on GitHub to verify that the checks appear.
3. You should see at least 3 checks:
   - `Local tests and APKs`
   - `Instrumented Tests (26)`
   - `Instrumented Tests (34)`
