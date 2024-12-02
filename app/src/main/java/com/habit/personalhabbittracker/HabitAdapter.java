package com.habit.personalhabbittracker;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private List<Habit> habitList;
    private OnHabitDeleteListener deleteListener;

    public interface OnHabitDeleteListener {
        void onDelete(int id);
    }

    public HabitAdapter(List<Habit> habitList, OnHabitDeleteListener deleteListener) {
        this.habitList = habitList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.name.setText(habit.getName());
        holder.description.setText(habit.getDescription());

        // Mark habit as completed
        holder.markCompletedButton.setOnClickListener(v -> {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            DatabaseHandler dbHandler = new DatabaseHandler(holder.itemView.getContext());
            dbHandler.addHabitCompletion(habit.getId(), currentDate);
            Toast.makeText(holder.itemView.getContext(), "Habit marked as completed!", Toast.LENGTH_SHORT).show();
        });

        // View history
        holder.viewHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), HabitHistoryActivity.class);
            intent.putExtra("habit_id", habit.getId());
            intent.putExtra("habit_name", habit.getName());
            holder.itemView.getContext().startActivity(intent);
        });

        // Delete habit
        holder.deleteButton.setOnClickListener(v -> deleteListener.onDelete(habit.getId()));
    }


    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        Button markCompletedButton, viewHistoryButton, deleteButton;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            name = itemView.findViewById(R.id.tvHabitName);
            description = itemView.findViewById(R.id.tvHabitDescription);
            markCompletedButton = itemView.findViewById(R.id.btnMarkCompleted);
            viewHistoryButton = itemView.findViewById(R.id.btnViewHistory);
            deleteButton = itemView.findViewById(R.id.btnDeleteHabit);
        }
    }
}

