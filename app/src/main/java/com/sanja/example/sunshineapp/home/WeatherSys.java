package com.sanja.example.sunshineapp.home;

import com.google.gson.annotations.SerializedName;

public class WeatherSys {
    @SerializedName("country") private String country;
    @SerializedName("sunrise") private long sunriseInMillis;
    @SerializedName("sunset") private long sunsetInMillis;

    public String getCountry() {
        return country;
    }

    public long getSunriseInMillis() {
        return sunriseInMillis;
    }

    public long getSunsetInMillis() {
        return sunsetInMillis;
    }
}