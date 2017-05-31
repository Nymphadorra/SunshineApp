package com.sanja.example.sunshineapp.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherResponse {
    @SerializedName("weather") private List<WeatherDescription> weatherDescription;
    @SerializedName("main") private WeatherDetails weatherDetails;
    @SerializedName("visibility") private int visibility;
    @SerializedName("wind") private Wind wind;
    @SerializedName("dt") private long dateInMillis;
    @SerializedName("sys") private WeatherSys searchMetaData;
    @SerializedName("id") private long id;
    @SerializedName("name") private String cityName;
    @SerializedName("cod") private int requestResponse;

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public long getDateInMillis() {
        return dateInMillis;
    }

    public WeatherSys getSearchMetaData() {
        return searchMetaData;
    }

    public long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public int getRequestResponse() {
        return requestResponse;
    }
}