package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.models.Workout;
import com.example.assignment2.models.WorkoutEntryAdapter;
import com.google.gson.Gson;

public class DetailedActivity extends AppCompatActivity {

    private ImageView imageView_backButton;
    private TextView textView_name;
    private TextView textView_type;
    private TextView textView_date;
    private TextView textView_cals;
    private TextView textView_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        getViews();

        Bundle bundle = getIntent().getExtras();
        String jsonEntry = bundle.getString(WorkoutEntryAdapter.ENTRY_KEY, null);

        if (jsonEntry != null) {
            Workout workoutEntry = new Gson().fromJson(jsonEntry, Workout.class);

            textView_name.setText(workoutEntry.getName());
            textView_date.setText(workoutEntry.getFormattedDate());
            textView_type.setText(workoutEntry.getType());
            textView_desc.setText(workoutEntry.getExtraText());
            textView_cals.setText(workoutEntry.getFormattedCalories());
        }

        imageView_backButton.setOnClickListener(e -> onBackPressed());


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
    }

    private void getViews() {
        this.textView_name = findViewById(R.id.detailed_title);
        this.textView_type = findViewById(R.id.detailed_type);
        this.textView_date = findViewById(R.id.detailed_date);
        this.textView_cals = findViewById(R.id.detailed_cals);
        this.textView_desc = findViewById(R.id.detailed_desc);

        this.imageView_backButton = findViewById(R.id.detailed_back_button);

    }
}