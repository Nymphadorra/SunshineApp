package com.sanja.example.sunshineapp.home;

import com.google.gson.annotations.SerializedName;

public class WeatherDetails {
    @SerializedName("temp") private double temperature;
    @SerializedName("tvPressure") private int pressure;
    @SerializedName("humidity") private int humidity;
    @SerializedName("temp_min") private int minTemp;
    @SerializedName("temp_max") private int maxTemp;

    public double getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }
}