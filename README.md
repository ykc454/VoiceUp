# VoiceUp – College Issue Reporting App

## Developer
**Yash Chaudhari**

---

## Project Description

VoiceUp is a modern Android application developed using **Kotlin, Jetpack Compose, MVVM Architecture,Clean Architecture, Hilt Dependency Injection, Room Database, Firebase Authentication, Firebase Firestore, and Kotlin Coroutines**.

The application provides a centralized digital platform for students to report, manage, and track college-related issues efficiently. Students can securely register or log in, submit complaints regarding academics, infrastructure, facilities, administration, parking, faculty concerns, and other campus-related problems.

VoiceUp streamlines communication between students and college authorities by replacing traditional manual complaint systems with a real-time digital reporting solution. The application supports both cloud-based data synchronization through Firebase Firestore and local offline storage using Room Database, ensuring reliable access to issue records.

The project follows **MVVM + Repository Pattern** principles to maintain a clean, scalable, and maintainable codebase while ensuring proper separation of concerns.

---

## Features

### Authentication
- Email & Password Authentication
- Secure User Registration
- User Login & Logout
- Session Management using Firebase Authentication

### Issue Management
- Submit College Issues
- Add Issue Subject & Description
- View Submitted Complaints
- Expand/Collapse Issue Details
- Real-Time Issue Updates

### Data Management
- Firebase Firestore Integration
- Offline Storage using Room Database
- Local Caching Support
- Repository Pattern Implementation

### User Experience
- Modern Jetpack Compose UI
- Material Design Components
- Responsive User Interface
- Smooth Navigation Experience

---

## Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/02b51a32-eab1-4b57-b295-7058e4991cc9" width="240" alt="Login Screen"/>
  &nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/b93ef1c8-c9d3-434e-9734-54ed0a3adc9a" width="240" alt="Issue List Screen"/>
  &nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/80bcefa9-c0db-410f-b1e6-8676c014ed42" width="240" alt="Issue Submission Screen"/>
</p>

## Architecture

The project follows **Clean Architecture + MVVM + Repository Pattern**.

```text
Presentation Layer
        │
        ▼
    ViewModels
        │
        ▼
      UseCases
        │
        ▼
 Repository Interfaces
        │
        ▼
Repository Implementation
        │
 ┌──────┴───────────────┐
 ▼                      ▼
Local Data Source   Remote Data Source
(Room Database)      Firebase
```

### Architecture Components

#### Presentation Layer

Contains Jetpack Compose screens, navigation, and ViewModels responsible for UI rendering, user interactions, and state management.

#### Domain Layer

Contains repository interfaces and use cases that define the application's business logic independent of implementation details.

#### Data Layer

Responsible for local Room database operations, Firebase services, data sources, and repository implementations.

#### Dependency Injection Layer

Uses Hilt to provide and manage dependencies throughout the application.

---

## Project Structure

```text
com.example.voiceup
│
├── data
│   │
│   ├── local
│   │   ├── IssueDao.kt
│   │   ├── IssueDatabase.kt
│   │   ├── IssueInfo.kt
│   │   └── IssueLocalDataSource.kt
│   │
│   ├── remote
│   │   ├── FirebaseAuthRepository.kt
│   │   └── IssueRemoteDataSource.kt
│   │
│   └── IssueRepositoryImpl.kt
│
├── dependencyInjection
│   └── DataBaseModule.kt
│
├── domain
│   │
│   ├── repo
│   │   ├── AuthRepository.kt
│   │   └── IssueRepository.kt
│   │
│   ├── usecase
│   │   ├── AddUseCase.kt
│   │   ├── DeleteUseCase.kt
│   │   ├── GetAllIssueUseCase.kt
│   │   ├── GetAllUseCase.kt
│   │   ├── SyncIssuesUseCase.kt
│   │   ├── UpdateUseCase.kt
│   │   └── IssuesUseCases.kt
│   │
│   └── Issue.kt
│
├── presentation
│   │
│   ├── screens
│   │   ├── SplashScreen.kt
│   │   ├── RoleSelectionScreen.kt
│   │   ├── StudentLoginScreen.kt
│   │   ├── TeacherLoginScreen.kt
│   │   ├── SignUpScreen.kt
│   │   ├── IssueListScreen.kt
│   │   ├── TeacherScreen.kt
│   │   └── FormScreen.kt
│   │
│   ├── viewmodels
│   │   ├── AuthViewModel.kt
│   │   └── IssueViewModel.kt
│   │
│   └── navigation.kt
│
├── ui.theme
│
├── VoiceUpApp.kt
│
└── MainActivity.kt
```

---

## Module Breakdown

### data/

Contains Room Database entities, DAOs, local and remote data sources, Firebase integrations, and repository implementations.

### local/

Handles offline issue storage, retrieval, caching, and Room database operations.

### remote/

Manages Firebase Authentication and Firestore synchronization.

### domain/

Contains repository contracts and use cases representing the application's core business logic.

### dependencyInjection/

Contains Hilt modules responsible for providing Room Database, repositories, and application-wide dependencies.

### presentation/

Contains Jetpack Compose screens, ViewModels, and Navigation components for managing UI and user interactions.

### VoiceUpApp.kt

Application class responsible for initializing Hilt and application-level configurations.

### MainActivity.kt

Main entry point that hosts the Compose UI and navigation graph.

---

## Tech Stack

### Language

* Kotlin

### UI

* Jetpack Compose
* Material Design 3

### Architecture

* Clean Architecture
* MVVM Architecture
* Repository Pattern
* Use Case Pattern

### Dependency Injection

* Hilt

### Local Storage

* Room Database

### Backend & Cloud

* Firebase Authentication
* Firebase Firestore

### Asynchronous Programming

* Kotlin Coroutines
* Flow / StateFlow

### Navigation

* Navigation Compose





## Contact

<p align="left">
  <a href="https://www.linkedin.com/in/yashchaudhari1605/">
    <img src="https://img.shields.io/badge/LinkedIn-Yash%20Chaudhari-blue?style=for-the-badge&logo=linkedin" />
  </a>
  <a href="mailto:yashchaudhari1605@gmail.com">
    <img src="https://img.shields.io/badge/Email-Contact%20Me-red?style=for-the-badge&logo=gmail" />
  </a>
</p>

---

### If you found this project helpful, consider giving it a star!
