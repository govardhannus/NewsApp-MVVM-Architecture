

# NewsApp - MVVM Architecture 🚀
Welcome to NewsApp, a powerful news application built with MVVM architecture, utilizing Coroutines, Dagger, and Jetpack components. This app includes unit and instrumentation testing for a robust and efficient experience, with a modern UI powered by Jetpack Compose.

## MVVM Architecture Diagram
![Architecture](https://github.com/govardhannus/NewsApp-MVVM-Architecture/blob/master/assets/News_app_architecture.png)

# Features Implemented
- **Top Headlines**: Displays top news articles from various sources.
- **Instant Search**: Instantly search for news articles using advanced Flow operators:
  - **Debounce**: Delays search results to improve performance.
  - **Filter**: Refines search results.
  - **DistinctUntilChanged**: Prevents duplicate searches.
  - **FlatMapLatest**: Provides real-time search updates.
- **Offline Support**: Access saved articles offline for a seamless experience.
- **Pagination**: Loads articles in batches for better performance.
- **Country and Language Filters**: Filter news by country, language, and sources for a personalized experience.
- **Multiple Sources**: Choose from multiple news sources for diverse perspectives.
- **Unit Testing**: Comprehensive unit tests with **Mockito** and **Turbine** for testing asynchronous streams.
- **UI Testing**: Automated UI testing with **Espresso** to ensure a smooth user experience.
- **Detailed Reading**: Opens articles in a WebView for an in-app, detailed reading experience.

## Dependencies
Add the following dependencies to your project:

- **UI**
  ```kotlin
  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.appcompat:appcompat:1.5.1")
  implementation("com.google.android.material:material:1.6.1")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.browser:browser:1.4.0")

- **Retrofit**
  ```kotlin
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")

- **Architectural Components**
  ```kotlin
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
 
- **Coroutines**
  ```kotlin
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

- **Dagger Hilt**
  ```kotlin
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-compiler:2.44")

- **Compose**
  ```kotlin
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation(composeBom)
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.navigation:navigation-compose:2.6.0")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
  implementation("io.coil-kt:coil-compose:2.4.0")

- **Room**
  ```kotlin
  implementation("androidx.room:room-runtime:2.5.0")
  implementation("androidx.room:room-ktx:2.5.0")
  kapt("androidx.room:room-compiler:2.5.0")

- **Paging 3**
  ```kotlin
  implementation("androidx.paging:paging-runtime-ktx:3.2.1")
  implementation("androidx.paging:paging-compose:3.2.1")
  
- **WorkManager**
  ```kotlin
  implementation("androidx.work:work-runtime-ktx:2.9.0")
  implementation("androidx.hilt:hilt-work:1.1.0")
  kapt("androidx.hilt:hilt-compiler:1.1.0")

- **Local Unit Test**
  ```kotlin
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:5.3.1")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
  testImplementation("app.cash.turbine:turbine:0.12.1")

- **Local Unit Test**
  ```kotlin
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
  androidTestImplementation(composeBom)
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
  
  
# Migration Guide: XML Project to Jetpack Compose

Follow these steps to migrate an existing XML-based Android project to Jetpack Compose:

1. **Upgrade Build Tools**: Update to the latest versions of the Android Gradle Plugin and Kotlin Plugin to ensure compatibility with Jetpack Compose.

2. **Add Jetpack Compose Dependencies**: Include the required Compose dependencies in your module-level `build.gradle` file.

3. **Create Composable Functions**: Start building your UI by creating `@Composable` functions to replace XML layouts.

4. **Convert XML Layouts to Compose**: Replace XML components like `TextView` with Compose elements such as `Text`.

5. **Transition to Compose Components**: Replace XML-based UI components with their Compose equivalents, including components like `Button`, `Text`, and `Column`.

6. **Implement Navigation with Compose**: Switch from XML-based navigation to Jetpack Compose’s navigation framework to manage screen transitions.

7. **Refactor UI Logic for Compose**: Adapt UI logic to utilize Compose’s state management, using tools like `remember` and `mutableStateOf`.

8. **Migrate App Resources**: Move resources (e.g., strings, colors, drawables) to formats compatible with Compose.

9. **Update Gradle Dependencies**: Ensure your Gradle dependencies are updated to support Jetpack Compose.

10. **Compose UI Testing**: Use the Compose testing library to write tests for your new Compose-based UI.

11. **Refer to Official Documentation**: Check out the official Jetpack Compose documentation and sample projects to understand concepts like Composables, state management, and navigation.

  
# Migration Guide: Dagger 2 to Dagger Hilt

Follow these steps to migrate an existing Dagger 2 project to Dagger Hilt:

1. **Add Hilt Dependencies**: Add the necessary Hilt dependencies in your module-level build.gradle file.

2. **Apply Hilt Gradle Plugin**: In build.gradle, apply the Hilt plugin with apply plugin: 'dagger.hilt.android.plugin'.

3. **Update Dagger Annotations**: Replace Dagger-specific annotations with Hilt annotations. For example, change @Component to @HiltComponent.

4. **Switch to Hilt’s Application Component**: Replace DaggerAppComponent from Dagger with Hilt’s Hilt_AppComponent.

5. **Annotate Application Class**: Add the @HiltAndroidApp annotation to your Application class to enable Hilt.

6. **Migrate Dagger Modules to Hilt Entry Points**: Replace Dagger modules with Hilt’s entry points for Android components.

7. **Update Injection in Activities and Fragments**: Replace Dagger injection in activities or fragments with Hilt’s injection approach.

8. **Update ViewModel Injection**: Replace Dagger’s ViewModelFactory with Hilt’s @HiltViewModel.

## Project Structure

The following is an overview of the project’s directory structure:

```plaintext
├── NewsApplication.kt
├── data
│   ├── api
│   │   ├── ApiKeyInterceptor.kt
│   │   └── NetworkService.kt
│   ├── local
│   │   ├── AppDatabase.kt
│   │   ├── AppDatabaseService.kt
│   │   ├── DatabaseService.kt
│   │   ├── dao
│   │   │   └── ArticleDao.kt
│   │   └── entity
│   │       ├── Article.kt
│   │       └── Source.kt
│   ├── model
│   │   ├── ApiArticle.kt
│   │   ├── ApiSource.kt
│   │   ├── Country.kt
│   │   ├── Language.kt
│   │   ├── NewsSourceResponse.kt
│   │   └── TopHeadlinesResponse.kt
│   ├── repository
│   │   ├── CountryRepository.kt
│   │   ├── LanguageRepository.kt
│   │   ├── NewsSourceRepository.kt
│   │   ├── OfflineArticleRepository.kt
│   │   ├── SearchRepository.kt
│   │   ├── TopHeadlinePagingRepository.kt
│   │   ├── TopHeadlinePagingSource.kt
│   │   └── TopHeadlineRepository.kt
│   └── worker
│       └── NewsWorker.kt
├── di
│   ├── module
│   │   └── ApplicationModule.kt
│   └── qualifiers.kt
├── ui
│   ├── base
│   │   ├── CommanUi.kt
│   │   ├── NewsNavigation.kt
│   │   └── UiState.kt
│   ├── country
│   │   ├── CountryScreen.kt
│   │   └── CountryViewModel.kt
│   ├── home
│   │   └── HomeScreen.kt
│   ├── language
│   │   ├── LanguageScreen.kt
│   │   └── LanguageViewModel.kt
│   ├── main
│   │   └── MainActivity.kt
│   ├── newssource
│   │   ├── NewsSourceScreen.kt
│   │   └── NewsSourceViewModel.kt
│   ├── search
│   │   ├── SearchScreen.kt
│   │   └── SearchViewModel.kt
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   ├── topheadline
│   │   ├── TopHeadlineScreen.kt
│   │   └── TopHeadlineViewModel.kt
│   ├── topheadlineoffline
│   │   ├── TopHeadlineOfflineScreen.kt
│   │   └── TopheadlineOfflineViewModel.kt
│   └── topheadlinepaging
│       ├── TopHeadlinePagingScreen.kt
│       └── TopHeadlinePagingViewModel.kt
└── utils
    ├── AppConstant.kt
    ├── DispatcherProvider.kt
    ├── NetworkHelper.kt
    └── TimeUtil.kt
``` 
## Screenshots 📱 
![News App](https://github.com/govardhannus/NewsApp-MVVM-Architecture/blob/master/assets/News_app.png)





