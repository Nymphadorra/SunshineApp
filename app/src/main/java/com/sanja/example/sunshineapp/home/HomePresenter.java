package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.CurrentWeatherResponse;
import com.sanja.example.sunshineapp.ForecastWeatherResponse;
import com.sanja.example.sunshineapp.WeatherDescription;
import com.sanja.example.sunshineapp.WeatherDetails;
import com.sanja.example.sunshineapp.core.api.APIService;
import com.sanja.example.sunshineapp.core.mvp.AbstractPresenter;
import com.sanja.example.sunshineapp.utils.Utils;
import com.sanja.example.sunshineapp.utils.constants.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePresenter extends AbstractPresenter<HomeMVP.View> implements HomeMVP.Presenter {
    private static final String MOCK_CITY_NAME = "Zagreb";
    private static final String UNIT = "metric";

    private final APIService apiService;

    public HomePresenter(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected void onBind() {
        super.onBind();
        refreshWeather();
    }

    @Override
    public void onSearchLocationClicked() {

    }

    @Override
    public void onRefreshClicked() {
        refreshWeather();
    }

    @Override
    public void onSettingsClicked() {

    }

    @Override
    public void onShareClicked() {

    }

    private void refreshCurrentWeather(String cityName, String unit, String apiKey) {
        apiService.getCurrentWeather(cityName, unit, apiKey).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                handleRefreshSuccessForCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });
    }

    private void refreshWeather(){
        refreshCurrentWeather(MOCK_CITY_NAME, UNIT, Constants.API_KEY);
        refreshForecastWeather(MOCK_CITY_NAME, UNIT, 11, Constants.API_KEY);
    }

    private void refreshForecastWeather(String cityName, String unit, int count, String apiKey) {
        apiService.getForecastWeather(cityName, unit, count, apiKey).enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                handleRefreshSuccessForecastWeather(response.body());
            }

            @Override
            public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {
            }
        });
    }

    private void handleRefreshSuccessForCurrentWeather(CurrentWeatherResponse response) {
        String cityName = response.getCityName();
        String date = Utils.getCurrentDate();
        WeatherDescription weatherDescription = response.getWeatherDescription().get(0);
        WeatherDetails weatherDetails = response.getWeatherDetails();
        double windSpeed = response.getWind().getSpeed();
        view().refreshCurrentWeatherUI(cityName, date, weatherDescription, weatherDetails, windSpeed);
    }

    private void handleRefreshSuccessForecastWeather(ForecastWeatherResponse response) {
        view().refreshForecastWeather(response.getForecasts());
    }
}