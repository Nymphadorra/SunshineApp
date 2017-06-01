package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.home.core.api.APIService;
import com.sanja.example.sunshineapp.home.core.api.mvp.AbstractPresenter;
import com.sanja.example.sunshineapp.home.utils.constants.Constants;
import com.sanja.example.sunshineapp.home.utils.constants.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePresenter extends AbstractPresenter<HomeMVP.View> implements HomeMVP.Presenter{
    private static final String MOCK_CITY_NAME = "Zagreb";
    private static final String UNIT = "metric";

    private final APIService apiService;

    public HomePresenter(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected void onBind() {
        super.onBind();
        refreshWeather(MOCK_CITY_NAME, UNIT, Constants.API_KEY);
    }

    private void refreshWeather(String cityName, String unit, String apiKey) {
        apiService.getCurrentWeather(cityName, unit, apiKey).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if(response.isSuccessful()) {
                    handleRefreshSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });
    }

    private void handleRefreshSuccess(CurrentWeatherResponse response){
        String cityName = response.getCityName();
        String date = Utils.getCurrentDate();
        List<WeatherDescription> weatherDescription = response.getWeatherDescription();
        WeatherDetails weatherDetails = response.getWeatherDetails();
        double windSpeed = response.getWind().getSpeed();
        view().refreshCurrentWeatherUI(cityName, date, weatherDescription, weatherDetails, windSpeed);
    }
}