package com.example.assignment2.models;

import java.util.Date;

public class Workout {

    private final String name;
    private final String type;
    private final int caloriesBurned;
    private final Date date;
    private final String extraText;

    public Workout(String name, String type, int caloriesBurned, Date date, String extraText) {
        this.name = name;
        this.type = type;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
        this.extraText = extraText;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public Date getDate() {
        return date;
    }

    public String getExtraText() {
        return extraText;
    }
}
