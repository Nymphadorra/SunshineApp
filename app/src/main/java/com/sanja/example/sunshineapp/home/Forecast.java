package com.sanja.example.sunshineapp.home;

public class Forecast {
    private String day;
    private String temperature;

    public Forecast(String day, String temperature) {
        this.day = day;
        this.temperature = temperature;
    }

    public String getDay() {
        return day;
    }

    public String getTemperature() {
        return temperature;
    }
}