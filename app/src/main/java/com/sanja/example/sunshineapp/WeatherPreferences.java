package com.sanja.example.sunshineapp;

import android.content.SharedPreferences;

public class WeatherPreferences {
    private static final String PREF_KEY_DEFAULT_LOCATION = "pref_key_default_location";
    private static final String PREF_KEY_UNIT = "pref_key_unit";
    private static final String PREF_KEY_FORECAST_COUNT = "pref_key_forecast_count";

    private final SharedPreferences sharedPreferences;

    public WeatherPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveLocation(String location){
        sharedPreferences
                .edit()
                .putString(PREF_KEY_DEFAULT_LOCATION, location)
                .apply();
    }

    public void saveUnit(String unit){
        sharedPreferences
                .edit()
                .putString(PREF_KEY_UNIT, unit)
                .apply();
    }

    public void saveForecastCount(int forecastCount){
        sharedPreferences
                .edit()
                .putInt(PREF_KEY_FORECAST_COUNT, forecastCount)
                .apply();
    }

    public String getLocation(){
        return sharedPreferences.getString(PREF_KEY_DEFAULT_LOCATION, "");
    }

    public String getUnit(){
        return sharedPreferences.getString(PREF_KEY_UNIT, "metric");
    }

    public int getForecastCount(){
        return sharedPreferences.getInt(PREF_KEY_FORECAST_COUNT, 7);
    }
}