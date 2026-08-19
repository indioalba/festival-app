# Implementation Plan - Fix Package Name Inconsistency

The project was recently refactored from `com.indioalba.festival` to `com.carbonbyte.sonfiestas`, but several files in the test source set still use the old package name. This causes Hilt's annotation processor to generate inconsistent code and ultimately leads to build failures because it cannot find the `FestivalApplication` class in the expected (old) package.

## Proposed Changes

### Test Source Set

I will update the following files in `app/src/test/java/com/carbonbyte/sonfiestas` to use the correct package name and update their internal references.

#### [MODIFY] [ExampleUnitTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/ExampleUnitTest.kt)
- Update package to `com.carbonbyte.sonfiestas`.

#### [MODIFY] [EventsViewModelTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/ui/events/EventsViewModelTest.kt)
- Update package to `com.carbonbyte.sonfiestas.ui.events`.
- Update imports from `com.indioalba.festival.*` to `com.carbonbyte.sonfiestas.*`.

#### [MODIFY] [OfflineFirstFestivalRepositoryTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/data/repository/OfflineFirstFestivalRepositoryTest.kt)
- Update package to `com.carbonbyte.sonfiestas.data.repository`.
- Update imports from `com.indioalba.festival.*` to `com.carbonbyte.sonfiestas.*`.

#### [MODIFY] [FestivalApiTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/data/remote/FestivalApiTest.kt)
- Update package to `com.carbonbyte.sonfiestas.data.remote`.

### Project Cleanup

#### [CLEANUP] Build Cache
- Run `./gradlew clean` to ensure all stale generated code in the `build/` directory is removed.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:hiltJavaCompileDemoDebug` to verify that Hilt code generation now works correctly.
- Run `./gradlew test` to ensure that the refactored tests still pass.

### Manual Verification
- Verify that `app/build/generated` no longer contains references to `com.indioalba.festival` after a fresh build.
