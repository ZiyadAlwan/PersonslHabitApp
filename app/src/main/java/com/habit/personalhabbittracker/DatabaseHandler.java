package com.habit.personalhabbittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HabitTracker";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_HABITS = "habits";
    private static final String TABLE_HISTORY = "history";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HABITS_TABLE = "CREATE TABLE habits (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "description TEXT)";
        db.execSQL(CREATE_HABITS_TABLE);

        String CREATE_HISTORY_TABLE = "CREATE TABLE history (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habit_id INTEGER, " +
                "date TEXT, " +
                "FOREIGN KEY(habit_id) REFERENCES habits(id))";
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS habits");
        db.execSQL("DROP TABLE IF EXISTS history");
        onCreate(db);
    }

    public void addHabitCompletion(int habitId, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("habit_id", habitId);
        values.put("date", date);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public List<String> getHabitHistory(int habitId) {
        List<String> history = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT date FROM history WHERE habit_id = ?", new String[]{String.valueOf(habitId)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Defensive check for column existence
                    int dateColumnIndex = cursor.getColumnIndex("date");
                    if (dateColumnIndex != -1) {
                        history.add(cursor.getString(dateColumnIndex));
                    } else {
                        Log.e("DatabaseError", "Column 'date' does not exist in the query result.");
                    }
                } while (cursor.moveToNext());
            } else {
                Log.d("DatabaseDebug", "No history found for habit_id: " + habitId);
            }
            cursor.close();
        } else {
            Log.e("DatabaseError", "Cursor is null for the query.");
        }

        db.close();
        return history;
    }

    public void addHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", habit.getName());
        values.put("description", habit.getDescription());
        db.insert(TABLE_HABITS, null, values);
    }

    public void deleteHabit(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABITS, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Habit> getAllHabits() {
        List<Habit> habitList = new ArrayList<>();
        String selectQuery = "SELECT * FROM habits";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Habit habit = new Habit();
                    habit.setId(cursor.getInt(0));
                    habit.setName(cursor.getString(1));
                    habit.setDescription(cursor.getString(2));
                    habitList.add(habit);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return habitList;
    }
}

