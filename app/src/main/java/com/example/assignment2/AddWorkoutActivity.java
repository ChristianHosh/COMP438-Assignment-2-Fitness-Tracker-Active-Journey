package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.assignment2.models.Workout;
import com.example.assignment2.models.WorkoutLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddWorkoutActivity extends AppCompatActivity {

    private Spinner spinner_type;

    private EditText editText_name;
    private EditText editText_caloriesBurned;
    private EditText editText_ExtraDescription;
    private AppCompatButton button_logWorkout;

    private final String[] workoutTypes = {"Running", "Strength", "Cycling", "Hiking", "Crossfit", "Yoga", "Cardio"};
    private final ArrayList<String> workoutTypesList = new ArrayList<>(Arrays.asList(workoutTypes));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        findViewsById();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, workoutTypesList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner_type.setAdapter(arrayAdapter);

        button_logWorkout.setOnClickListener(e -> logWorkout());


    }

    private void logWorkout() {
        String name = editText_name.getText().toString().trim();
        String type = spinner_type.getSelectedItem().toString().trim();
        int caloriesBurned = Integer.parseInt(editText_caloriesBurned.getText().toString().trim());
        Date date = new Date();
        String extraDescription = editText_ExtraDescription.getText().toString().trim();

        Workout newWorkoutEntry = new Workout(name, type, caloriesBurned, date, extraDescription);

        WorkoutLog.addWorkoutToLog(newWorkoutEntry);

        finish();
    }

    private void findViewsById() {
        editText_name = findViewById(R.id.add_edit_name);
        spinner_type = findViewById(R.id.add_spinner_type);
        editText_caloriesBurned = findViewById(R.id.add_edit_calories);
        editText_ExtraDescription = findViewById(R.id.add_edit_extra);
        button_logWorkout = findViewById(R.id.add_button_submit);

    }
}