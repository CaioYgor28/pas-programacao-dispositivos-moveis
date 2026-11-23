# Jardim Viver — Android Registration App

An academic Android project for managing parents' and guardians' registration details. Built as part of a mobile development course, it demonstrates form validation, local persistence, and basic CRUD operations in Java.

## Features

- Register a guardian's first name, last name, email, age, and address.
- Validate required fields, email format, and age range.
- Store records locally using SQLite.
- Display entries with Android's `RecyclerView`.
- Edit and delete existing records.

## Technology

- **Language:** Java 17
- **Platform:** Android (minimum API 24, target SDK 34)
- **Interface:** AndroidX and Material Components
- **Persistence:** SQLite via `SQLiteOpenHelper`
- **Build:** Gradle; GitHub Actions workflow available for debug APK builds

## Project structure

```text
app/src/main/java/br/com/techz/jardimviver/
├── MainActivity.java           Form interactions and CRUD flow
├── data/DatabaseHelper.java    SQLite persistence
├── model/ResponsavelDTO.java   Guardian data model
└── ui/ResponsavelAdapter.java  RecyclerView list adapter

app/src/main/res/layout/
├── activity_main.xml           Registration form
└── item_responsavel.xml        List entry
```

## Run in Android Studio

1. Install Android Studio, JDK 17, and Android SDK 34.
2. Clone the repository and open its root directory in Android Studio.
3. Let Gradle sync the project. If required, configure a compatible Gradle 8.7 wrapper.
4. Start an Android emulator (API 24+) or connect an Android device.
5. Run the `app` configuration.

`local.properties` is intentionally excluded because the Android SDK path differs across machines.

## Build using GitHub Actions

The workflow at `.github/workflows/build-apk.yml` configures the Android build environment and produces a debug APK artifact. See [the build guide (Portuguese)](COMO_GERAR_APK_NO_GITHUB.md) for instructions.

## Scope

This is a **course assignment**, not a production student-management system. Records are stored locally on the Android device; the application does not implement a remote API or multi-user cloud synchronization.
