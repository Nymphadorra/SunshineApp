package com.sanja.example.sunshineapp.core.api;

import com.sanja.example.sunshineapp.weather.CurrentWeatherResponse;
import com.sanja.example.sunshineapp.weather.ForecastWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("weather?")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String cityName,
            @Query("units") String unit,
            @Query("APPID") String apiKey);

    @GET("forecast/daily?")
    Call<ForecastWeatherResponse> getForecastWeather(
            @Query("q") String cityName,
            @Query("units") String unit,
            @Query("cnt") int count,
            @Query("APPID") String apiKey);

}