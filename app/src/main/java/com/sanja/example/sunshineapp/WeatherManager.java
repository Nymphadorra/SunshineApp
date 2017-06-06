package com.sanja.example.sunshineapp;

public class WeatherManager {
    private WeatherPreferences weatherPreferences;
    private String location;
    private String unit;
    private int forecastCount;
    private boolean isSettingsEmpty = false;

    public WeatherManager(WeatherPreferences weatherPreferences) {
        this.weatherPreferences = weatherPreferences;
        this.location = weatherPreferences.getLocation();
        this.unit = weatherPreferences.getUnit();
        this.forecastCount = weatherPreferences.getForecastCount();
        if(location.isEmpty()){
            isSettingsEmpty = true;
        }
    }

    public String getLocation() {
        return location;
    }

    public String getUnit() {
        return unit;
    }

    public int getForecastCount() {
        return forecastCount;
    }

    public boolean isSettingsEmpty() {
        return isSettingsEmpty;
    }

    public void setLocation(String location) {
        this.location = location;
        weatherPreferences.saveLocation(location);
    }

    public void setUnit(String unit) {
        this.unit = unit;
        weatherPreferences.saveUnit(unit);
    }

    public void setForecastCount(int forecastCount) {
        this.forecastCount = forecastCount;
        weatherPreferences.saveForecastCount(forecastCount);
    }

}