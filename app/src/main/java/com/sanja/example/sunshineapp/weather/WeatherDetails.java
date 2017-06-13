package com.sanja.example.sunshineapp.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherDetails {
    @SerializedName("temp") private double temperature;
    @SerializedName("tvPressure") private int pressure;
    @SerializedName("humidity") private int humidity;
    @SerializedName("temp_min") private double minTemp;
    @SerializedName("temp_max") private double maxTemp;

    public double getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }
}