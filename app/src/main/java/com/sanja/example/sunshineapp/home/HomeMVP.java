package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.Forecast;
import com.sanja.example.sunshineapp.WeatherDescription;
import com.sanja.example.sunshineapp.WeatherDetails;

import java.util.List;

public interface HomeMVP {
    interface View {
        void refreshCurrentWeatherUI(String cityName,
                                     String date,
                                     WeatherDescription weatherDescription,
                                     WeatherDetails weatherDetails,
                                     double windSpeed);

        void refreshForecastWeather(List<Forecast> forecasts);
    }

    interface Presenter extends com.sanja.example.sunshineapp.core.mvp.BasePresenter<View> {
        void onSearchLocationClicked();

        void onRefreshClicked();

        void onSettingsClicked();

        void onShareClicked();
    }
}