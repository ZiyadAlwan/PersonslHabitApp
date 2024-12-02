package com.habit.personalhabbittracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class HabitHistoryActivity extends AppCompatActivity {
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);
        Button btnBackToHabitList = findViewById(R.id.btnBackToHabitList);
        TextView tvHabitHistoryTitle = findViewById(R.id.tvHabitHistoryTitle);
        ListView lvHabitHistory = findViewById(R.id.lvHabitHistory);


        DatabaseHandler dbHandler = new DatabaseHandler(this);

        findViewById(R.id.btnBackToHabitList).setOnClickListener(v -> finish());

        int habitId = getIntent().getIntExtra("habit_id", -1);
        String habitName = getIntent().getStringExtra("habit_name");

        tvHabitHistoryTitle.setText("History for " + habitName);
        List<String> history = dbHandler.getHabitHistory(habitId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        lvHabitHistory.setAdapter(adapter);
    }

}
