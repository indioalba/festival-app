# Design Context: Festival App

This document summarizes the current architectural and UI design patterns implemented in the Festival project.

## 🏛️ Architecture
The project follows **Google's Official Clean Architecture** guidance, ensuring a scalable and maintainable codebase.

- **Domain Layer**: Contains business logic and models (e.g., `Event`, `EventCategory`).
- **Data Layer**: Implements an **Offline-First** strategy.
    - **Local**: Room database (`FestivalDatabase`) for caching.
    - **Remote**: Retrofit (`FestivalApi`) for network requests.
    - **Repository**: `OfflineFirstFestivalRepository` acts as the mediator between local and remote sources.
- **Presentation Layer**: Utilizes the **MVI (Model-View-Intent)** pattern with a **Reducer**.
    - **State**: `EventsUiState` is the single source of truth for the UI.
    - **Intent**: `EventsIntent` captures all user actions.
    - **Reducer**: A pure function in `EventsViewModel` that calculates state transitions using the `scan` operator.

## 🎨 UI & UX Design
- **Toolkit**: Built entirely with **Jetpack Compose** and **Material 3**.
- **Layouts**: Adaptive layouts designed to support various screen sizes.
- **Components**:
    - `EventList`: A `LazyColumn` for efficient scrolling of agenda items.
    - `EventItem`: Displays title, date, time, and category with a **Favorite toggle** (heart icon).
    - **Offline Indicator**: A red "Offline Mode" banner that appears at the top when the `ConnectivityObserver` detects no internet.
- **Theming**: Custom `FestivalTheme` using Material 3 color schemes.

## 🛠️ Tech Stack & Tools
- **Language**: Kotlin
- **Dependency Injection**: Hilt
- **Asynchronous Programming**: Coroutines & Flows
- **Database**: Room
- **Networking**: Retrofit & OkHttp
- **Serialization**: Kotlinx Serialization
- **Testing**:
    - **Unit Tests**: Junit 4, Turbine (for Flows).
    - **Screenshot Tests**: Roborazzi (JVM-based).
- **Code Quality**: Spotless (ktlint), Dependency Guard.

## 🔄 Development Workflow
- **VCS**: Git (GitHub).
- **CI**: GitHub Actions runs automated checks on every commit/PR.
- **Automation**: Reviewer subagent performs automated reviews and merges upon CI success.
