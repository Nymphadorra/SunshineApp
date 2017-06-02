package com.sanja.example.sunshineapp.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.home.di.components.AppComponent;
import com.sanja.example.sunshineapp.home.di.components.DaggerHomeComponent;
import com.sanja.example.sunshineapp.home.di.components.HomeComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements HomeMVP.View{
    @Inject HomeMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_city) TextView tvCity;
    @BindView(R.id.tv_temperature) TextView tvCurrentTemperature;
    @BindView(R.id.tv_current_date) TextView tvCurrentDate;
    @BindView(R.id.tv_weather_description) TextView tvCurrentDescription;
    @BindView(R.id.tv_pressure) TextView tvPressure;
    @BindView(R.id.tv_wind_speed) TextView tvWindSpeed;
    @BindView(R.id.tv_minimum_temperature) TextView tvMinTemperature;
    @BindView(R.id.tv_maximum_temperature) TextView tvMaxTemperature;
    @BindView(R.id.tv_weather_update) TextView tvWeatherUpdate;
    @BindView(R.id.iv_current_weather_state) ImageView ivCurrentWeatherState;
    @BindView(R.id.rv_forecast) RecyclerView rvForecast;

    private ForecastAdapter forecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar(toolbar, R.string.toolbar_title_main, false);

        rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        forecastAdapter = new ForecastAdapter();
        rvForecast.setAdapter(forecastAdapter);

        presenter.bind(this);
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .build();
        homeComponent.inject(this);
    }

    @Override
    public void refreshCurrentWeatherUI(String cityName,
                                        String date,
                                        List<WeatherDescription> weatherDescription,
                                        WeatherDetails weatherDetails,
                                        double windSpeed) {
        tvCity.setText(cityName);
        tvCurrentTemperature.setText(String.valueOf(weatherDetails.getTemperature()));
        tvCurrentDate.setText(date);
        tvCurrentDescription.setText(weatherDescription.get(0).getDescription().toUpperCase());
        tvPressure.setText("Pressure: " + String.valueOf(weatherDetails.getPressure()) + " hPa");
        tvWindSpeed.setText("Wind: " + String.valueOf(windSpeed) + " km/h");
        tvMinTemperature.setText("Min: " + String.valueOf(weatherDetails.getMinTemp()) + "°C");
        tvMaxTemperature.setText("Max: " + String.valueOf(weatherDetails.getMaxTemp()) + "°C");
        tvWeatherUpdate.setText("Last updated: Just now");
    }

    @Override
    public void refreshForecastWeather(List<Forecast> forecasts) {
        forecastAdapter.refreshForecast(forecasts);
    }

    @OnClick(R.id.rl_current_weather)
    public void onCurrentWeatherClicked(){
        showToast("Details will open!");
    }
}