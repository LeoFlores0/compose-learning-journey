# Android Learning Journey

This repository serves as a portfolio of my Android development projects, focusing on modern architecture and Jetpack Compose.

## Projects

### [Count to Five](https://github.com/LeoFlores0/compose-learning-journey/tree/main/Count-to-Five)
A responsive image gallery application that demonstrates advanced list handling and adaptive layout transitions.
* **Key Features:**
    * **Adaptive Layouts:** Switches dynamically between a `LazyColumn` for portrait mode and a `LazyRow` for landscape mode.
    * **Sticky Headers:** Utilizes `stickyHeader` functionality to maintain context while scrolling through the image list.
    * **Orientation Awareness:** Monitors device state via `LocalConfiguration` to provide a seamless user experience during rotation.
* **Tech Stack:** Kotlin, Jetpack Compose, ViewModel.

### [Car Loan Calculator](https://github.com/LeoFlores0/compose-learning-journey/tree/main/Car-Loan-Calculator)
A financial utility app that calculates monthly car payments based on user input, interest rates, and loan terms.
* **Key Features:** * Responsive design with unique layouts for **Portrait** and **Landscape** modes.
    * Real-time interest rate adjustment via a **Slider** component.
    * Loan term selection using **RadioButtons**.
    * Dynamic payment calculation using the standard amortization formula.
* **Tech Stack:** Kotlin, Jetpack Compose, ViewModel, Material3.

### [Whack-a-Mole](https://github.com/LeoFlores0/compose-learning-journey/tree/main/Whack-a-Mole)
An interactive game that tests reaction speed through dynamic state updates and timed event loops.
* **Key Features:**
    * **Game Logic:** Implements a scoring and "miss" system that tracks user accuracy in real-time.
    * **Concurrency:** Uses `LaunchedEffect` and `delay` to control target visibility and game pacing asynchronously.
    * **Dynamic Modes:** Features a "Random Mode" toggle using a Checkbox to switch between predictable and randomized gameplay patterns.
* **Tech Stack:** Kotlin, Jetpack Compose, Coroutines.

### [Color Chase](https://github.com/LeoFlores0/compose-learning-journey/tree/main/Color-Chase)
A visual sequence application that demonstrates asynchronous UI updates and state-controlled logic.
* **Key Features:** * **State-Driven Sequences:** Utilizes `LaunchedEffect` and `delay` to manage a high-speed color chasing effect.
    * **Dynamic Patterns:** Supports both a predictable clockwise sequence and a randomized mode.
    * **Interactive Controls:** Toggle functionality for the sequence loop and mode switching via a Checkbox.
* **Tech Stack:** Kotlin, Jetpack Compose, Coroutines (delay).

### [Slot Machine](https://github.com/LeoFlores0/compose-learning-journey/tree/main/Slot-Machine)
A dynamic game built with **Jetpack Compose** that demonstrates asynchronous state management and precise coroutine control.
* **Key Features:**
    * **Coroutine Lifecycle Management:** Utilizes `Job` tracking and `rememberCoroutineScope` to manage three independent reels, allowing for individual "STOP" functionality.
    * **Asynchronous Logic:** Implements staggered spin speeds using `delay` and `Dispatchers.Default` to simulate realistic reel movement.
    * **State-Driven Win Logic:** Features a centralized validation system that evaluates game results only after all asynchronous reel jobs have been successfully cancelled.
* **Tech Stack:** Kotlin, Jetpack Compose, Coroutines (Jobs/Scopes), ViewModel.---

## Technical Skills Demonstrated

* **Architecture & State Management:**
    * **MVVM Pattern:** Separating business logic from UI using `ViewModel` for cleaner, testable code.
    * **State Handling:** Proficient in `remember`, `mutableStateOf`, and `mutableIntStateOf` to drive dynamic UI updates.
* **Jetpack Compose UI:**
    * **Declarative Layouts:** Building responsive interfaces with `Column`, `Row`, and `Box`.
    * **Advanced Lists:** Implementing `LazyColumn` and `LazyRow` with `stickyHeader` functionality.
    * **Adaptive Design:** Using `LocalConfiguration` to handle orientation changes (Portrait vs. Landscape).
    * **Material 3 Components:** Integration of `Scaffold`, `Button`, `Slider`, `RadioButton`, and `TextField`.
* **Concurrency & Logic:**
    * **Kotlin Coroutines:** Managing asynchronous tasks using `LaunchedEffect`, `delay`, and `Dispatchers`.
    * **Lifecycle Management:** Controlling background tasks through manual `Job` cancellation and `CoroutineScope`.
* **Tools & Standards:**
    * **Version Control:** Git/GitHub with strict adherence to **Conventional Commits** standards.
    * **Build Systems:** Project configuration using **Gradle (KTS)** and Android Studio.