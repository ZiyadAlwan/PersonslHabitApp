package com.habit.personalhabbittracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HabitListActivity extends AppCompatActivity {
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btnBackToMain = findViewById(R.id.btnBackToMain);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbHandler = new DatabaseHandler(this);

        List<Habit> habitList = dbHandler.getAllHabits();
        if (habitList.isEmpty()) {
            Toast.makeText(this, "No habits found. Add some habits first.", Toast.LENGTH_SHORT).show();
        } else {
            recyclerView.setAdapter(new HabitAdapter(habitList, id -> {
                dbHandler.deleteHabit(id);
                Toast.makeText(this, "Habit deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }));
        }

        btnBackToMain.setOnClickListener(v -> finish());
    }
}