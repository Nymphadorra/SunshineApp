package com.sanja.example.sunshineapp;

import com.google.gson.annotations.SerializedName;

public class ForecastTemperature {
    @SerializedName("day") private double temperature;

    public double getTemperature() {
        return temperature;
    }
}