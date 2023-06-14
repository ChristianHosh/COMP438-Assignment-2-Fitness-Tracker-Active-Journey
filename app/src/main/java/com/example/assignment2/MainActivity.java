package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.assignment2.models.Workout;
import com.example.assignment2.models.WorkoutEntryAdapter;
import com.example.assignment2.models.WorkoutLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String WORKOUTS_KEY = "LOCAL_SAVED_WORKOUTS";


    private RecyclerView recyclerView_workouts;
    @SuppressWarnings("FieldCanBeLocal")
    private AppCompatButton button_addWorkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView_workouts = findViewById(R.id.main_recycler);
        button_addWorkout = findViewById(R.id.main_add_workout);

        button_addWorkout.setOnClickListener(e -> openAddWorkoutActivity());

        recyclerView_workouts.setLayoutManager(new LinearLayoutManager(this));

        loadLogEntries();

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LIFECYCLE", "STOPPED");
        saveWorkoutsToLocalStorage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LIFECYCLE", "RESUMED");
        reloadRecycler();
    }

    private void loadLogEntries() {
        Log.i("APP","LOADED ENTRIES");
        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String workoutJson = preferences.getString(WORKOUTS_KEY, null);
        if (workoutJson == null) return;

        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<Workout>>() {
        }.getType();

        ArrayList<Workout> workouts = gson.fromJson(workoutJson, listType);
        WorkoutLog.clear();
        for (Workout workout : workouts) {
            WorkoutLog.addWorkoutToLog(workout);
        }

        reloadRecycler();
    }

    private void reloadRecycler() {
        Log.i("APP","RELOADED ENTRIES");
        WorkoutEntryAdapter workoutEntryAdapter = new WorkoutEntryAdapter(MainActivity.this);
        recyclerView_workouts.setAdapter(workoutEntryAdapter);
    }

    private void saveWorkoutsToLocalStorage() {
        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();

        ArrayList<Workout> workouts = WorkoutLog.getWorkouts();

        String workoutsToJSON = gson.toJson(workouts);

        editor.putString(WORKOUTS_KEY, workoutsToJSON);
        editor.apply();
    }


    private void openAddWorkoutActivity() {
        Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
        startActivity(intent);
    }
}