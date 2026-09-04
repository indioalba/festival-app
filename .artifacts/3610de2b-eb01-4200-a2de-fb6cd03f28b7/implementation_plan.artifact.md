# Fix Hilt Build Error: Missing FestivalApplication Class

The project fails to build with the error `Could not find class file for 'com.indioalba.festival.FestivalApplication'`. This is likely due to the recent refactoring from the `com.indioalba.festival` package to `com.carbonbyte.sonfiestas`, where some test files still retain the old package declaration and imports, causing confusion in the Hilt annotation processor.

## Proposed Changes

### [app]

The following test files are currently in the correct directory (`com/carbonbyte/sonfiestas/...`) but have incorrect `package` declarations and `import` statements referring to the old `com.indioalba.festival` package.

#### [MODIFY] [ExampleUnitTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/ExampleUnitTest.kt)
- Update package to `com.carbonbyte.sonfiestas`.

#### [MODIFY] [FestivalApiTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/data/remote/FestivalApiTest.kt)
- Update package to `com.carbonbyte.sonfiestas.data.remote`.

#### [MODIFY] [OfflineFirstFestivalRepositoryTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/data/repository/OfflineFirstFestivalRepositoryTest.kt)
- Update package to `com.carbonbyte.sonfiestas.data.repository`.
- Update imports from `com.indioalba.festival.*` to `com.carbonbyte.sonfiestas.*`.

#### [MODIFY] [EventsViewModelTest.kt](file:///Users/indioalba/Workspace/Festival/app/src/test/java/com/carbonbyte/sonfiestas/ui/events/EventsViewModelTest.kt)
- Update package to `com.carbonbyte.sonfiestas.ui.events`.
- Update imports from `com.indioalba.festival.*` to `com.carbonbyte.sonfiestas.*`.

#### [MODIFY] [AndroidManifest.xml](file:///Users/indioalba/Workspace/Festival/app/src/main/AndroidManifest.xml)
- Change `android:name=".FestivalApplication"` to the fully qualified name `android:name="com.carbonbyte.sonfiestas.FestivalApplication"` to avoid any ambiguity during Hilt code generation.

## Verification Plan

### Automated Tests
- Run `./gradlew clean :app:hiltJavaCompileDemoDebug` to verify that Hilt can now find the application class and generate the necessary components.
- Run all unit tests to ensure the package refactoring didn't break anything: `./gradlew :app:testDemoDebugUnitTest`.

### Manual Verification
- Verify that the project builds successfully in Android Studio.
