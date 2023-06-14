package com.example.assignment2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WorkoutEntryAdapter extends RecyclerView.Adapter<WorkoutEntryAdapter.ViewHolder> {

    private final static String CALORIES_EXTRA = " Kcal \uD83D\uDD25";
    private Context context;
    private final ArrayList<Workout> workoutList = new ArrayList<>();

    public WorkoutEntryAdapter(Context context) {
        this.context = context;
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
        TextView card_textName = (TextView)cardView.findViewById(R.id.card_text_name);
        TextView card_textType = (TextView)cardView.findViewById(R.id.card_text_type);
        TextView card_textDate = (TextView)cardView.findViewById(R.id.card_text_date);
        TextView card_textCals = (TextView)cardView.findViewById(R.id.card_text_calories);
        TextView card_textDesc = (TextView)cardView.findViewById(R.id.card_text_description);


        String entryCaloriesText = entry.getCaloriesBurned() + CALORIES_EXTRA;

        card_textName.setText(entry.getName());
        card_textType.setText(entry.getType());
        card_textDate.setText(formatDate(entry.getDate()));
        card_textCals.setText(entryCaloriesText);
        card_textDesc.setText(entry.getExtraText());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    private String formatDate(Date date){
        String pattern = "dd MMM yyyy";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("en"));

        return formatter.format(date);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }
}
