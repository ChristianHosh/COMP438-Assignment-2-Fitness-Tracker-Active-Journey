package com.example.assignment2.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Workout {

    private final String name;
    private final String type;
    private final int caloriesBurned;
    private final Date date;
    private final String extraText;

    private final static String CALORIES_EXTRA = " Kcal \uD83D\uDD25";
    private final static String pattern = "dd MMM yyyy";
    private final static SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("en"));

    public Workout(String name, String type, int caloriesBurned, Date date, String extraText) {
        this.name = name;
        this.type = type;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
        this.extraText = extraText;
    }

    public String getFormattedDate(){
        return formatter.format(this.date);
    }

    public String getFormattedCalories(){
        return this.caloriesBurned + CALORIES_EXTRA;

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
