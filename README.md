# MobileCompose

A modern Android application built with **Jetpack Compose** and **Kotlin**, following Google's recommended architecture and Material Design 3 guidelines.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Build & Run](#build--run)
- [Testing](#testing)
- [Architecture](#architecture)
- [Theming & Styling](#theming--styling)
- [Configuration](#configuration)
- [Dependencies](#dependencies)
- [Contributing](#contributing)

---

## Overview

**MobileCompose** is a starter/template Android project that demonstrates how to set up a fully modern Android app using:

- **Jetpack Compose** for declarative UI
- **Material 3** for design components and theming
- **Edge-to-edge** display support
- **Dynamic Color** (Android 12+ wallpaper-based theming)
- **Kotlin-first** development

The app currently displays a greeting screen and serves as a clean foundation for building feature-rich Android applications.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin 2.0.21 |
| UI Toolkit | Jetpack Compose (BOM 2024.09.00) |
| Design System | Material Design 3 |
| Build System | Gradle 9.0.1 (Kotlin DSL) |
| Min Android SDK | API 24 (Android 7.0 Nougat) |
| Target Android SDK | API 36 |
| JVM Compatibility | Java 11 |

---

## Project Structure

```
MobileCompose/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/mobilecompose/
│   │   │   │   ├── MainActivity.kt          # Entry point, Compose host
│   │   │   │   └── ui/
│   │   │   │       └── theme/
│   │   │   │           ├── Color.kt         # Brand color palette
│   │   │   │           ├── Theme.kt         # Light/Dark/Dynamic theme
│   │   │   │           └── Type.kt          # Typography definitions
│   │   │   ├── AndroidManifest.xml
│   │   │   └── res/                         # Resources (icons, strings, etc.)
│   │   ├── test/                            # Unit tests
│   │   └── androidTest/                     # Instrumented (UI) tests
│   ├── build.gradle.kts                     # App-level build config
│   └── proguard-rules.pro
├── gradle/
│   └── libs.versions.toml                   # Version catalog (single source of truth)
├── build.gradle.kts                         # Root build config
├── settings.gradle.kts                      # Module settings
└── gradle.properties                        # JVM & Gradle flags
```

---

## Prerequisites

Before you begin, make sure you have the following installed:

- **Android Studio** Hedgehog (2023.1.1) or later (Meerkat recommended)
- **JDK 11** or later
- **Android SDK** with API level 24–36 installed
- **Git**

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/MobileCompose.git
cd MobileCompose
```

### 2. Open in Android Studio

- Launch **Android Studio**
- Click **File → Open**
- Navigate to the cloned directory and click **OK**
- Wait for Gradle sync to complete

### 3. Sync Gradle

If Gradle doesn't sync automatically:

```
File → Sync Project with Gradle Files
```

---

## Build & Run

### Run on Emulator or Device

1. Connect a physical device via USB (with USB debugging enabled) **or** start an Android Virtual Device (AVD) from Android Studio.
2. Select your target device from the device dropdown in the toolbar.
3. Click the **▶ Run** button or press `Shift + F10`.

### Build from Command Line

**Debug APK:**
```bash
./gradlew assembleDebug
```

**Release APK:**
```bash
./gradlew assembleRelease
```

**Install directly on connected device:**
```bash
./gradlew installDebug
```

Built APKs are output to:
```
app/build/outputs/apk/
```

### Clean Build

```bash
./gradlew clean
```

---

## Testing

### Unit Tests

Runs on the local JVM (no device required):

```bash
./gradlew test
```

Test files are located at:
```
app/src/test/java/com/example/mobilecompose/
```

### Instrumented Tests (UI Tests)

Requires a connected device or emulator:

```bash
./gradlew connectedAndroidTest
```

Test files are located at:
```
app/src/androidTest/java/com/example/mobilecompose/
```

Test frameworks used:
- **JUnit 4** for unit testing
- **Espresso** for UI interaction testing
- **Compose UI Test (junit4)** for Compose-specific UI testing

---

## Architecture

The project follows a single-activity architecture powered entirely by Jetpack Compose.

```
MainActivity (ComponentActivity)
└── setContent {
      MobileComposeTheme {          ← applies Material3 theming
        Scaffold {                  ← provides layout structure
          Greeting()                ← composable UI function
        }
      }
    }
```

**Key architectural decisions:**

- **Single Activity** — The app uses one `ComponentActivity` as the Compose host. Navigation between screens (when added) should use the Compose Navigation library.
- **Composable Functions** — All UI is built as stateless composable functions; state should be hoisted upward following Compose best practices.
- **Edge-to-Edge** — `enableEdgeToEdge()` is called in `onCreate` to render content behind the system bars for a full-bleed look.

---

## Theming & Styling

The theme is defined in `ui/theme/` and uses **Material Design 3**.

### Color Palette (`Color.kt`)

| Token | Light | Dark |
|---|---|---|
| Primary | `Purple40` `#6650A4` | `Purple80` `#D0BCFF` |
| Secondary | `PurpleGrey40` `#625B71` | `PurpleGrey80` `#CCC2DC` |
| Tertiary | `Pink40` `#7D5260` | `Pink80` `#EFB8C8` |

### Dynamic Color

On **Android 12+** (API 31+), the app automatically uses **Dynamic Color** — the system extracts colors from the user's wallpaper for a personalized experience. This behavior is controlled by the `dynamicColor` parameter in `MobileComposeTheme`.

```kotlin
MobileComposeTheme(
    darkTheme = isSystemInDarkTheme(),
    dynamicColor = true  // enabled by default on Android 12+
)
```

### Typography (`Type.kt`)

Uses the default Material3 `Typography` with a customized `bodyLarge`:

| Style | Font Family | Weight | Size | Line Height |
|---|---|---|---|---|
| `bodyLarge` | Default | Normal | 16sp | 24sp |

---

## Configuration

### `gradle.properties`

| Property | Value | Purpose |
|---|---|---|
| `org.gradle.jvmargs` | `-Xmx2048m` | Sets max JVM heap for Gradle daemon |
| `android.useAndroidX` | `true` | Enables AndroidX libraries |
| `kotlin.code.style` | `official` | Enforces official Kotlin code style |
| `android.nonTransitiveRClass` | `true` | Reduces R class size for faster builds |

### Version Catalog (`gradle/libs.versions.toml`)

All dependency versions are centralized in the **Gradle Version Catalog**. To update a library version, edit the `[versions]` block in `libs.versions.toml`:

```toml
[versions]
agp = "9.0.1"
kotlin = "2.0.21"
composeBom = "2024.09.00"
```

---

## Dependencies

### Core

| Library | Version | Purpose |
|---|---|---|
| `androidx.core:core-ktx` | 1.17.0 | Kotlin extensions for Android core APIs |
| `androidx.lifecycle:lifecycle-runtime-ktx` | 2.10.0 | Lifecycle-aware coroutines |
| `androidx.activity:activity-compose` | 1.12.4 | Compose integration in Activity |

### Jetpack Compose (via BOM 2024.09.00)

| Library | Purpose |
|---|---|
| `compose.ui` | Core Compose UI engine |
| `compose.ui.graphics` | Drawing and graphics primitives |
| `compose.ui.tooling.preview` | `@Preview` annotation support |
| `compose.material3` | Material Design 3 components |
| `compose.ui.tooling` *(debug)* | Layout inspector support |
| `compose.ui.test.manifest` *(debug)* | Test manifest for Compose tests |

### Testing

| Library | Version | Purpose |
|---|---|---|
| `junit:junit` | 4.13.2 | Unit testing framework |
| `androidx.test.ext:junit` | 1.3.0 | AndroidX JUnit extensions |
| `androidx.test.espresso:espresso-core` | 3.7.0 | UI interaction testing |
| `compose.ui.test.junit4` | (BOM) | Compose UI test rules |

---

## Contributing

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/your-feature`
3. **Commit** your changes: `git commit -m "Add your feature"`
4. **Push** to the branch: `git push origin feature/your-feature`
5. **Open a Pull Request**

Please follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html) and ensure all tests pass before submitting.

---

## License

```
Copyright (c) 2026 MobileCompose

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
