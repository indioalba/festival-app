# Implementation Plan - Fix Hilt Build Error: Missing FestivalApplication Class

The project fails to build with the error `Could not find class file for 'com.indioalba.festival.FestivalApplication'`. This is due to a package refactoring from `com.indioalba.festival` to `com.carbonbyte.sonfiestas` where several test files were left with the old package name and stale imports. This inconsistency causes Hilt's annotation processor to look for the application class in the old package.

## Proposed Changes

### Test Source Set
Update the following files in `app/src/test/java/com/carbonbyte/sonfiestas` to use the correct package name and update their internal references.

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
