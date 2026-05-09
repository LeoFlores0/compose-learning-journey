# Android Learning Journey

This repository serves as a portfolio of my Android development projects, focusing on modern architecture and Jetpack Compose.

## Projects

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
A game built using **Jetpack Compose** that demonstrates UI state management and animations.
* **Key Features:** Randomized reel logic, state-driven UI updates.
* **Tech Stack:** Kotlin, Compose, ViewModel.

---

## Technical Skills Demonstrated
* **Architecture:** MVVM (Model-View-ViewModel) for separating business logic from the UI.
* **UI & UX:** * Declarative UI with **Jetpack Compose**.
    * Handling **Configuration Changes** (Landscape vs. Portrait) using `LocalConfiguration`.
    * Material 3 components (Scaffold, Slider, RadioButton, TextField, Checkbox).
* **Tools:** Git/GitHub, Android Studio, Gradle (KTS).
* **Logic:** Implementing mathematical formulas, coroutine-based timing loops, and comprehensive state handling for user interactions.