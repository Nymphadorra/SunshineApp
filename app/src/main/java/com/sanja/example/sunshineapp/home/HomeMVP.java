package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.CurrentWeather;
import com.sanja.example.sunshineapp.weather.Forecast;
import com.sanja.example.sunshineapp.weather.WeatherDescription;
import com.sanja.example.sunshineapp.weather.WeatherDetails;
import com.sanja.example.sunshineapp.core.mvp.BasePresenter;

import java.util.List;

public interface HomeMVP {
    interface View {
        void refreshCurrentWeather(CurrentWeather currentWeather);

        void refreshForecastWeather(List<Forecast> forecasts);

        void showSearchBox();

        void hideSearchBox();

        void startSettingsActivity();

        void showEmptySettingsScreen();

        void showNetworkError();
    }

    interface Presenter extends BasePresenter<View> {
        void onSearchLocationClicked();

        void onRefreshClicked();

        void onSettingsClicked();

        void onShareClicked();

        void onLocationSelected(String selectedLocation);

        void onSettingsActivityFinished();
    }
}