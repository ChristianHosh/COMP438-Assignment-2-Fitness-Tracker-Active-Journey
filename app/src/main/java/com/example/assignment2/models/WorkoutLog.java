package com.example.assignment2.models;

import android.content.SharedPreferences;

import com.example.assignment2.MainActivity;

import java.util.ArrayList;

public class WorkoutLog {

    private final static ArrayList<Workout> workouts = new ArrayList<>();



    public static void addWorkoutToLog(Workout newWorkoutEntry){
        workouts.add(newWorkoutEntry);
    }

    public static ArrayList<Workout> getWorkouts(){
        return workouts;
    }

    public static void clear() {
        workouts.clear();
    }
}
