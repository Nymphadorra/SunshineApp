package com.sanja.example.sunshineapp.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription {

    @SerializedName("id") private int id;
    @SerializedName("main") private String mainWeatherState;
    @SerializedName("description") private String description;
    @SerializedName("icon") private String icon;

    public int getId() {
        return id;
    }

    public String getMainWeatherState() {
        return mainWeatherState;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}