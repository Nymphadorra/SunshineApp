package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.home.core.api.mvp.BasePresenter;

import java.util.List;

public interface HomeMVP {
    interface View {
        void refreshCurrentWeatherUI(String cityName,
                                     String date,
                                     List<WeatherDescription> weatherDescription,
                                     WeatherDetails weatherDetails,
                                     double windSpeed);

        void refreshForecastWeather(List<Forecast> forecasts);
    }

    interface Presenter extends BasePresenter<View> {
        void onSearchLocationClicked();

        void onRefreshClicked();

        void onSettingsClicked();

        void onShareClicked();
    }
}