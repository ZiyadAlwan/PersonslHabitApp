# Personal Habit Tracker App

## Overview
The **Personal Habit Tracker App** is an Android application designed to help users create, track, and manage their daily habits. The app provides features to add new habits, view a list of existing habits, mark them as completed, and view the completion history. It includes basic data persistence using SQLite and a user-friendly interface.

---

## Features
- **Add Habits**: Users can create new habits by providing a name and description.
- **View All Habits**: A list of all added habits is displayed, with options to mark them as completed, view history, or delete them.
- **Mark Habits as Completed**: Users can log the completion of a habit for the current date.
- **View Completion History**: A detailed history of a habit’s completion dates is available.
- **Delete Habits**: Remove habits from the list and database.

---

## Technologies Used
- **Programming Language**: Java
- **Database**: SQLite
- **UI Components**: RecyclerView, ListView, ScrollView, ConstraintLayout, LinearLayout
- **Development Tools**: Android Studio, Android Debug Bridge (ADB)

---

## Project Structure
```
PersonalHabitTracker/
├── src/main/
│   ├── java/com/habit/personalhabbittracker/
│   │   ├── MainActivity.java                # Main entry point and navigation hub
│   │   ├── AddHabitActivity.java            # Activity to add new habits
│   │   ├── HabitListActivity.java           # Activity to display all habits
│   │   ├── HabitHistoryActivity.java        # Activity to display completion history
│   │   ├── Habit.java                       # Model class for Habit
│   │   ├── DatabaseHandler.java             # Handles SQLite database operations
│   │   ├── HabitAdapter.java                # Adapter for RecyclerView in HabitListActivity
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml            # Layout for MainActivity
│   │   │   ├── activity_add_habit.xml       # Layout for AddHabitActivity
│   │   │   ├── activity_habit_list.xml      # Layout for HabitListActivity
│   │   │   ├── activity_habit_history.xml   # Layout for HabitHistoryActivity
│   │   │   ├── item_habit.xml               # Layout for each habit item in RecyclerView
│   │   ├── values/
│   │   │   ├── strings.xml                  # Strings for localization
│   │   │   ├── colors.xml                   # App color themes
│   │   │   ├── themes.xml                   # App-wide themes and styles
├── AndroidManifest.xml                      # App's activity declarations
```

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repository-url.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files to ensure all dependencies are installed.
4. Run the app on an emulator or a physical device.

---

## Usage
1. Launch the app to open the **MainActivity**.
2. Use the following features:
   - **Add Habit**: Navigate to `AddHabitActivity` and create a new habit.
   - **View All Habits**: See the list of all habits in `HabitListActivity`.
   - **Mark as Completed**: Log a completion for any habit.
   - **View History**: Check the completion history for a habit.
   - **Delete Habit**: Remove a habit permanently.

---

## Database Schema
### Tables
1. **`habits`**
   - `id` (INTEGER, Primary Key)
   - `name` (TEXT)
   - `description` (TEXT)

2. **`history`**
   - `id` (INTEGER, Primary Key)
   - `habit_id` (INTEGER, Foreign Key referencing `habits.id`)
   - `date` (TEXT)

---

## Future Enhancements
- Add reminders using notifications.
- Implement a feature to edit habits.
- Visual progress tracking for habit completions.
- Cloud sync for cross-device access.

---
