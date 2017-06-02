package com.sanja.example.sunshineapp.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("dt") private long date;
    @SerializedName("temp") private ForecastTemperature forecastTemperature;
    @SerializedName("weather") private List<WeatherDescription> weatherDescription;

    public long getDate() {
        return date;
    }

    public ForecastTemperature getForecastTemperature() {
        return forecastTemperature;
    }

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }
}