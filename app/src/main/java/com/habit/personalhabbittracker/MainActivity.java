package com.habit.personalhabbittracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddHabit = findViewById(R.id.btnAddHabit);
        Button btnViewHabits = findViewById(R.id.btnViewHabits);

        btnAddHabit.setOnClickListener(v -> startActivity(new Intent(this, AddHabitActivity.class)));
        btnViewHabits.setOnClickListener(v -> startActivity(new Intent(this, HabitListActivity.class)));
    }
}


