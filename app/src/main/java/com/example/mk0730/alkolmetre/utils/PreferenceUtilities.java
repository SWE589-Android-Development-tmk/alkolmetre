package com.example.mk0730.alkolmetre.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtilities {
    public static final String KEY_WATER_COUNT = "water-count";
    public static final String KEY_CHARGING_REMINDER_COUNT = "charging-reminder-count";

    private static final int DEFAULT_COUNT = 0;


    synchronized private static void setWaterCount(Context context, int glassesOfWater) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_WATER_COUNT, glassesOfWater);
        editor.apply();
    }

    public static int getWaterCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int glassesOfWater = prefs.getInt(KEY_WATER_COUNT, DEFAULT_COUNT);
        return glassesOfWater;
    }

    synchronized public static void incrementWaterCount(Context context) {
        int waterCount = PreferenceUtilities.getWaterCount(context);
        PreferenceUtilities.setWaterCount(context, ++waterCount);
    }

}
