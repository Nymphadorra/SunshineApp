package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.weather.Forecast;
import com.sanja.example.sunshineapp.weather.WeatherDescription;
import com.sanja.example.sunshineapp.weather.WeatherDetails;
import com.sanja.example.sunshineapp.core.mvp.BasePresenter;

import java.util.List;

public interface HomeMVP {
    interface View {
        void refreshCurrentWeather(String cityName,
                                   String date,
                                   WeatherDescription weatherDescription,
                                   WeatherDetails weatherDetails,
                                   double windSpeed);

        void refreshForecastWeather(List<Forecast> forecasts);

        void showSearchBox();

        void hideSearchBox();
    }

    interface Presenter extends BasePresenter<View> {
        void onSearchLocationClicked();

        void onRefreshClicked();

        void onSettingsClicked();

        void onShareClicked();

        void onLocationSelected(String selectedLocation);
    }
}