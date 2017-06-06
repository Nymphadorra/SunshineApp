package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.WeatherManager;
import com.sanja.example.sunshineapp.weather.CurrentWeatherResponse;
import com.sanja.example.sunshineapp.weather.ForecastWeatherResponse;
import com.sanja.example.sunshineapp.weather.WeatherDescription;
import com.sanja.example.sunshineapp.weather.WeatherDetails;
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
    private static final int MOCK_COUNT = 10;

    private final APIService apiService;
    private final WeatherManager weatherManager;

    public HomePresenter(APIService apiService, WeatherManager weatherManager) {
        this.apiService = apiService;
        this.weatherManager = weatherManager;
    }

    @Override
    protected void onBind() {
        super.onBind();
        refreshWeather(MOCK_CITY_NAME, UNIT, Constants.API_KEY, MOCK_COUNT);
    }

    @Override
    public void onSearchLocationClicked() {
        view().showSearchBox();
    }

    @Override
    public void onRefreshClicked() {
        //refreshWeather();
    }

    @Override
    public void onSettingsClicked() {

    }

    @Override
    public void onShareClicked() {

    }

    @Override
    public void onLocationSelected(String selectedLocation) {
        refreshWeather(selectedLocation, UNIT, Constants.API_KEY, MOCK_COUNT);
        view().hideSearchBox();
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

    private void refreshWeather(String cityName, String unit, String apiKey, int forecastCount){
        refreshCurrentWeather(cityName, unit, apiKey);
        refreshForecastWeather(cityName, unit, forecastCount, apiKey);
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
        view().refreshCurrentWeather(cityName, date, weatherDescription, weatherDetails, windSpeed);
    }

    private void handleRefreshSuccessForecastWeather(ForecastWeatherResponse response) {
        view().refreshForecastWeather(response.getForecasts());
    }
}