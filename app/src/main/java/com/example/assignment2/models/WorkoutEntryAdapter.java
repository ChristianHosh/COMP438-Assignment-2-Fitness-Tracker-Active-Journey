package com.example.assignment2.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.DetailedActivity;
import com.example.assignment2.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkoutEntryAdapter extends RecyclerView.Adapter<WorkoutEntryAdapter.ViewHolder> {

    public final static  String ENTRY_KEY = "WORKOUT_ENTRY";

    private final Context context;
    private final Activity activity;
    private final ArrayList<Workout> workoutList = new ArrayList<>();

    public WorkoutEntryAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

        workoutList.clear();
        workoutList.addAll(WorkoutLog.getWorkouts());
    }

    @NonNull
    @Override
    public WorkoutEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cardview_workout_entry, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutEntryAdapter.ViewHolder holder, int position) {
        final Workout entry = workoutList.get(position);
        CardView cardView = holder.cardView;




        holder.card_textName.setText(entry.getName());
        holder.card_textType.setText(entry.getType());
        holder.card_textDate.setText(entry.getFormattedDate());
        holder.card_textCals.setText(entry.getFormattedCalories());

        cardView.setOnClickListener(e -> {
            String jsonEntry = new Gson().toJson(entry);

            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra(ENTRY_KEY, jsonEntry);

            context.startActivity(intent);
            activity.overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
        });
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView card_textName;
        private final TextView card_textType;
        private final TextView card_textDate;
        private final TextView card_textCals;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.card_textName = cardView.findViewById(R.id.card_text_name);
            this.card_textType = cardView.findViewById(R.id.card_text_type);
            this.card_textDate = cardView.findViewById(R.id.card_text_date);
            this.card_textCals = cardView.findViewById(R.id.card_text_calories);

            this.cardView = cardView;
        }

    }
}
