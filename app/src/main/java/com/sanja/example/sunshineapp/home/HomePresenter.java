package com.sanja.example.sunshineapp.home;


import com.sanja.example.sunshineapp.CurrentWeather;
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
    private String selectedLocation;
    private String selectedUnit;
    private int selectedForecastCount = 8;

    public HomePresenter(APIService apiService, WeatherManager weatherManager) {
        this.apiService = apiService;
        this.weatherManager = weatherManager;
    }

    @Override
    protected void onBind() {
        super.onBind();
        if(weatherManager.getLocation().isEmpty()){
            view().showEmptySettingsScreen();
        } else {
            getPreferenceData();
            refreshWeather(selectedLocation, selectedUnit, Constants.API_KEY, selectedForecastCount);
        }
    }

    @Override
    public void onSearchLocationClicked() {
        view().showSearchBox();
    }

    @Override
    public void onRefreshClicked() {
        refreshWeather(selectedLocation, selectedUnit, Constants.API_KEY, selectedForecastCount);
    }

    @Override
    public void onSettingsClicked() {
        view().startSettingsActivity();
    }

    @Override
    public void onShareClicked() {

    }

    @Override
    public void onLocationSelected(String selectedLocation) {
        this.selectedLocation = selectedLocation;
        refreshWeather(selectedLocation, selectedUnit, Constants.API_KEY, selectedForecastCount);
        view().hideSearchBox();
    }

    @Override
    public void onSettingsActivityFinished() {
        getPreferenceData();
        refreshWeather(selectedLocation, selectedUnit, Constants.API_KEY, selectedForecastCount);
    }

    private void refreshWeather(String location, String unit, String apiKey, int forecastCount) {
        refreshCurrentWeather(location, unit, apiKey);
        refreshForecastWeather(location, unit, forecastCount, apiKey);
    }

    private void refreshCurrentWeather(String cityName, String unit, String apiKey) {
        apiService.getCurrentWeather(cityName, unit, apiKey).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                handleRefreshSuccessForCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                handleRefreshFailure();
            }
        });
    }

    private void refreshForecastWeather(String cityName, String unit, int count, String apiKey) {
        apiService.getForecastWeather(cityName, unit, count, apiKey).enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                handleRefreshSuccessForecastWeather(response.body());
            }

            @Override
            public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {
                handleRefreshFailure();
            }
        });
    }

    private void handleRefreshSuccessForCurrentWeather(CurrentWeatherResponse response) {
        String location = response.getCityName();
        String date = Utils.getCurrentDate();
        WeatherDescription weatherDescription = response.getWeatherDescription().get(0);
        WeatherDetails weatherDetails = response.getWeatherDetails();
        double windSpeed = response.getWind().getSpeed();
        CurrentWeather currentWeather = new CurrentWeather(
                location,
                date,
                weatherDescription.getDescription(),
                weatherDescription.getIcon(),
                weatherDetails,
                windSpeed);
        view().refreshCurrentWeather(currentWeather);
    }

    private void handleRefreshSuccessForecastWeather(ForecastWeatherResponse response) {
        view().refreshForecastWeather(response.getForecasts());
    }

    private void handleRefreshFailure() {
        view().showNetworkError();
    }

    private void getPreferenceData(){
        selectedLocation = weatherManager.getLocation();
        selectedUnit = weatherManager.getUnit();
        // selectedForecastCount = weatherManager.getForecastCount();
    }
}