# News-App - MVVM architecture

<p align="center"><img src="https://github.com/mayursarode4/News-App/blob/main/assets/News-app-mvvm.png?description=1&forks=1&language=1&name=1&owner=1&pattern=Circuit%20Board&pulls=1&stargazers=1&theme=Dark"></p>



## Major Highlights

- **Jetpack Compose** for modern UI
- **Offline caching** with a **single source of truth**
- **MVVM architecture** for a clean and scalable codebase
- **Kotlin** and **Groovy**
- **Dagger Hilt** for efficient dependency injection.
- **Retrofit** for seamless networking
- **Room DB** for local storage of news articles
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for streamlined state management
- **Pagination** to efficiently load and display news articles
- **Unit tests** and **UI tests** for robust code coverage
- **Instant search** for quick access to relevant news
- **Navigation** for smooth transitions between screens
- **Custom Tabs** for a seamless reading experience directly within the app
- **WorkManager** for periodic news fetching
- **Notification** for alerting about latest news
- **Coil** for efficient image loading

<p align="center">
<img alt="mvvm-architecture"  src="https://github.com/mayursarode4/News-App/blob/main/assets/News_app_mvvm_architecture.JPG">
</p>

## Features Implemented

- Show top news articles
- News by country, language, and source
- Search for specific news articles
- View news articles in a Android browser Custom Tabs for a detailed reading experience
- Implements a caching mechanism to store news articles locally. Allows users to access previously viewed top news articles even when offline.
- Implements pagination for efficient loading of large sets of news articles.

## Dependency Use

- Jetpack Compose for UI: Modern UI toolkit for building native Android UIs
- Coil for Image Loading: Efficiently loads and caches images
- Retrofit for Networking: A type-safe HTTP client for smooth network requests
- Dagger Hilt for Dependency Injection: Simplifies dependency injection
- Room for Database: A SQLite object mapping library for local data storage
- Paging Compose for Pagination: Simplifies the implementation of paginated lists
- Mockito, JUnit, Turbine for Testing: Ensures the reliability of the application

## How to Run the Project

- Clone the Repository:
```
git clone https://github.com/mayursarode4/News-App.git
cd News-App
```
- Visit newsapi.org and sign up for an API key, Copy the API key provided
- Open the build.gradle file in the app module. Find the following line
```
buildConfigField("String", "API_KEY", "\"<Add your API Key>\"")
```
- Replace "Add your API Key" with the API key you obtained
- Build and run the NewsApp.


## The Complete Project Folder Structure

```
└───com
    └───mayursarode
        └───newsapp
            │   NewsApplication.kt
            │
            ├───data
            │   ├───api
            │   │       ApiKeyInterceptor.kt
            │   │       NetworkService.kt
            │   │
            │   ├───local
            │   │   ├───dao
            │   │   │       ArticleDao.kt
            │   │   │
            │   │   ├───database
            │   │   │       DatabaseService.kt
            │   │   │       NewsDatabase.kt
            │   │   │       NewsDatabaseService.kt
            │   │   │
            │   │   └───entity
            │   │           Article.kt
            │   │           Source.kt
            │   │
            │   ├───model
            │   │       ApiArticle.kt
            │   │       ApiSource.kt
            │   │       Country.kt
            │   │       Language.kt
            │   │       NewsSourcesResponse.kt
            │   │       TopHeadlinesResponse.kt
            │   │
            │   └───repository
            │           CountryRepository.kt
            │           LanguageRepository.kt
            │           NewsBySourcesRepository.kt
            │           NewsSourcesRepository.kt
            │           SearchRepository.kt
            │           TopHeadlinesPagingSource.kt
            │           TopHeadlinesRepository.kt
            │
            ├───di
            │   │   qualifiers.kt
            │   │
            │   └───module
            │           ApplicationModule.kt
            │
            ├───navigation
            │       NewsNavigation.kt
            │       Screen.kt
            │
            ├───ui
            │   │   HomeScreen.kt
            │   │   MainActivity.kt
            │   │
            │   ├───base
            │   │       BaseViewModel.kt
            │   │       CommonUI.kt
            │   │       UiState.kt
            │   │
            │   ├───country
            │   │       CountryListScreen.kt
            │   │       CountryListViewModel.kt
            │   │
            │   ├───language
            │   │       LanguageListScreen.kt
            │   │       LanguageListViewModel.kt
            │   │
            │   ├───newsbysources
            │   │       NewsBySourcesScreen.kt
            │   │       NewsBySourcesViewModel.kt
            │   │
            │   ├───newssources
            │   │       NewsSourcesScreen.kt
            │   │       NewsSourcesViewModel.kt
            │   │
            │   ├───search
            │   │       SearchScreen.kt
            │   │       SearchViewModel.kt
            │   │
            │   ├───theme
            │   │       Color.kt
            │   │       Theme.kt
            │   │       Type.kt
            │   │
            │   └───topheadlines
            │       ├───offline
            │       │       TopHeadlinesOfflineScreen.kt
            │       │       TopHeadlinesOfflineViewModel.kt
            │       │       
            │       ├───online
            │       │       TopHeadlinesOnlineScreen.kt
            │       │       TopHeadlinesOnlineViewModel.kt
            │       │
            │       └───paging
            │               TopHeadlinesPagingScreen.kt
            │               TopHeadlinesPagingViewModel.kt
            │
            ├───utils
            │   │   Constants.kt
            │   │   DispatcherProvider.kt
            │   │   LocalData.kt
            │   │   ResourceProvider.kt
            │   │   TimeUtil.kt
            │   │
            │   ├───logger
            │   │       AppLogger.kt
            │   │       Logger.kt
            │   │
            │   └───network
            │           DefaultNetworkHelper.kt
            │           NetworkHelper.kt
            │
            └───worker
                    NewsWorker.kt



```


<h2>App Screenshots:</h2>
<p align="center">
<img alt="screenshots"  src="https://github.com/mayursarode4/News-App/blob/main/assets/screenshot-1.JPG">
<img alt="screenshots"  src="https://github.com/mayursarode4/News-App/blob/main/assets/screenshot-2.JPG">
</p>



## If this project helps you, show love ❤️ by putting a ⭐ on this project ✌️

## Contribute to the project

Feel free to improve or add features to the project.
Create an issue or find the pending issue. All pull requests are welcome 😄
