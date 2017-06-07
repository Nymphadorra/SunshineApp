package com.sanja.example.sunshineapp;

import com.sanja.example.sunshineapp.weather.WeatherDetails;

public class CurrentWeather {
    private String location;
    private String date;
    private String weatherDescription;
    private String icon;
    private WeatherDetails weatherDetails;
    private double windSpeed;

    public CurrentWeather(String location, String date, String weatherDescription, String icon, WeatherDetails weatherDetails, double windSpeed) {
        this.location = location;
        this.date = date;
        this.weatherDescription = weatherDescription;
        this.icon = icon;
        this.weatherDetails = weatherDetails;
        this.windSpeed = windSpeed;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}