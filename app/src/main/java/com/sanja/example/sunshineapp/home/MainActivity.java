package com.sanja.example.sunshineapp.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends BaseActivity implements HomeMVP.View{
    @Inject HomeMVP.Presenter presenter;

    @BindView(R.id.tv_city) TextView city;
    @BindView(R.id.tv_temperature) TextView currentTemperature;
    @BindView(R.id.tv_current_date) TextView currentDate;
    @BindView(R.id.tv_weather_description) TextView currentDescription;
    @BindView(R.id.tv_pressure) TextView pressure;
    @BindView(R.id.tv_wind_speed) TextView currentWindSpeed;
    @BindView(R.id.tv_minimum_temperature) TextView minTemperature;
    @BindView(R.id.tv_maximum_temperature) TextView maxTemperature;
    @BindView(R.id.tv_weather_update) TextView weatherUpdate;
    @BindView(R.id.iv_current_weather_state) ImageView currentWeatherState;
    @BindView(R.id.rv_forecast) RecyclerView rvForecast;

    private ForecastAdapter forecastAdapter;
    private List<Forecast> forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        forecast = new ArrayList<>();
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));
        forecast.add(new Forecast("SUN", "30"));

        rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        forecastAdapter = new ForecastAdapter();
        rvForecast.setAdapter(forecastAdapter);
        forecastAdapter.refreshForecast(forecast);

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
        city.setText(cityName);
        currentTemperature.setText(String.valueOf(weatherDetails.getTemperature()));
        currentDate.setText(date);
        currentDescription.setText(weatherDescription.get(0).getDescription().toUpperCase());
        pressure.setText(String.valueOf(weatherDetails.getPressure()));
        currentWindSpeed.setText(String.valueOf(windSpeed));
        minTemperature.setText(String.valueOf(weatherDetails.getMinTemp()));
        maxTemperature.setText(String.valueOf(weatherDetails.getMaxTemp()));
        weatherUpdate.setText("Just now");
    }
}