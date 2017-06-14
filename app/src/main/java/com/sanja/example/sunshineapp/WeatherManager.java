package com.sanja.example.sunshineapp;

public class WeatherManager {
    private WeatherPreferences weatherPreferences;
    private boolean isChangeMade;


    public WeatherManager(WeatherPreferences weatherPreferences) {
        this.weatherPreferences = weatherPreferences;
        this.isChangeMade = false;
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

    public void isChangeMade(boolean isChangeMade){
        this.isChangeMade = isChangeMade;
    }

    public boolean isChangeMade(){
        return isChangeMade;
    }
}