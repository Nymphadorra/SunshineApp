package com.sanja.example.sunshineapp.home.core.api;

import com.sanja.example.sunshineapp.home.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIService {
    @GET("weather?")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String cityName,
            @Query("units") String unit,
            @Query("APPID") int apiKey);
}