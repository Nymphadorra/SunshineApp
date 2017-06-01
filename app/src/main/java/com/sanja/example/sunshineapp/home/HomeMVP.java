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
    }

    interface Presenter extends BasePresenter<View> {
    }
}