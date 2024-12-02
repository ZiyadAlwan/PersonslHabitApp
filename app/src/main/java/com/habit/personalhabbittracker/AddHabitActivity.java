package com.habit.personalhabbittracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        dbHandler = new DatabaseHandler(this); // Initialize the database handler

        EditText etHabitName = findViewById(R.id.etHabitName);
        EditText etHabitDescription = findViewById(R.id.etHabitDescription);
        Button btnSaveHabit = findViewById(R.id.btnSaveHabit);
        Button btnBack = findViewById(R.id.btnBack);
        // Handle back button
        btnBack.setOnClickListener(f -> {
            //return to previous activity
            finish();
        });
        btnSaveHabit.setOnClickListener(v -> {
            String name = etHabitName.getText().toString().trim();
            String description = etHabitDescription.getText().toString().trim();

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save habit to the database
            Habit habit = new Habit(name, description);
            dbHandler.addHabit(habit);

            Toast.makeText(this, "Habit saved successfully!", Toast.LENGTH_SHORT).show();

            // Clear the fields
            etHabitName.setText("");
            etHabitDescription.setText("");

            // Optional: Close activity after saving
            finish();
        });
    }
}
