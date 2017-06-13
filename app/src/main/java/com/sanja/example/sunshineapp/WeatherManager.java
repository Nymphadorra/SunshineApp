package com.sanja.example.sunshineapp;

public class WeatherManager {
    private WeatherPreferences weatherPreferences;

    public WeatherManager(WeatherPreferences weatherPreferences) {
        this.weatherPreferences = weatherPreferences;
    }

    public String getLocation() {
        return weatherPreferences.getLocation();
    }

    public String getUnit() {
        return weatherPreferences.getUnit();
    }

    public int getForecastCount() {
        return weatherPreferences.getForecastCount();
    }
}