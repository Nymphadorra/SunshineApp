package com.sanja.example.sunshineapp.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastWeatherResponse {
    @SerializedName("list") private List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}