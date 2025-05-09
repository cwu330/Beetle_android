# 🪲 Beetle Dice Game

This is an Android app built with Kotlin that simulates a two-player Beetle Dice Game. Players take turns rolling a die to gradually build a beetle. The first player to complete their beetle wins!

## 🎮 Gameplay Rules

- Players roll a 6-sided dice.
- Each roll corresponds to a beetle body part:
  - `6`: Body (must be added first)
  - `5`: Head (requires body)
  - `4`: Legs (requires body, 6 total)
  - `3`: Antennae (requires head, 2 total)
  - `2`: Eyes (requires head, 2 total)
  - `1`: Tail (requires body)
- Only one part can be added per successful roll.
- Each successful addition increases the player's score.
- The first player to complete their beetle wins.

## 🛠 Features

- Two-player turn-based play.
- Visual dice rolling and beetle image display on win.
- Score tracking for each player.
- Reset button to restart the game at any time.

## 🧪 How to Run

1. **Clone the repo or download the project.**

2. **Open in Android Studio.**

3. **Build & run the project** using an emulator or a physical Android device.

   - Requires:
     - Kotlin
     - Android Studio Arctic Fox or newer
     - Gradle setup with view binding and lifecycle dependencies

## 📁 Project Structure

- `MainActivity.kt`: Handles UI and user interaction.
- `GameViewModel.kt`: Contains game logic and score tracking.
- `Beetle.kt`: Data model for the beetle.
- `res/layout/activity_main.xml`: UI layout.
- `res/drawable/`: Contains dice faces, beetle image, and background.
