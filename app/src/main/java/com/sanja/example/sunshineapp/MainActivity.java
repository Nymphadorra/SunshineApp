package com.sanja.example.sunshineapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.sanja.example.sunshineapp.di.components.AppComponent;
import com.sanja.example.sunshineapp.di.components.DaggerHomeComponent;
import com.sanja.example.sunshineapp.di.components.HomeComponent;
import com.sanja.example.sunshineapp.home.HomeMVP;
import com.sanja.example.sunshineapp.weather.Forecast;
import com.sanja.example.sunshineapp.weather.ForecastAdapter;
import com.sanja.example.sunshineapp.weather.WeatherDescription;
import com.sanja.example.sunshineapp.weather.WeatherDetails;
import com.sanja.example.sunshineapp.weather.WeatherIconSelector;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements HomeMVP.View {
    @Inject HomeMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;
    @BindView(R.id.view_animator) ViewAnimatorById viewAnimator;
    @BindView(R.id.et_search_location) EditText etSearchLocation;
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

    private boolean editTextFocused = false;
    private ForecastAdapter forecastAdapter;
    private String textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar(toolbar, R.string.toolbar_title_main, false);
        setViewAnimatorAnimations(this, viewAnimator);

        viewAnimator.setChild(R.id.pb_loading_spinner);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_item_search_locations).setVisible(!editTextFocused);
        menu.findItem(R.id.menu_item_refresh).setVisible(!editTextFocused);
        menu.findItem(R.id.menu_item_check).setVisible(editTextFocused);
        menu.findItem(R.id.menu_item_close).setVisible(editTextFocused);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_search_locations):
                presenter.onSearchLocationClicked();
                return true;
            case (R.id.menu_item_refresh):
                presenter.onRefreshClicked();
                showToast(R.string.msg_refreshed);
                viewAnimator.setChild(R.id.ll_manage_settings);
                return true;
            case (R.id.menu_item_check):
                if (isTextInputValid()) {
                    presenter.onLocationSelected(textInput);
                    return true;
                } else {
                    return false;
                }
            case (R.id.menu_item_close):
                hideSearchBox();
            case (R.id.menu_item_settings):
                presenter.onSettingsClicked();
                return true;
            case (R.id.menu_item_share):
                presenter.onShareClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void refreshCurrentWeather(String cityName,
                                      String date,
                                      WeatherDescription weatherDescription,
                                      WeatherDetails weatherDetails,
                                      double windSpeed) {
        tvCity.setText(cityName);
        tvCurrentTemperature.setText(String.valueOf(weatherDetails.getTemperature()));
        tvCurrentDate.setText(date);
        tvCurrentDescription.setText(weatherDescription.getDescription().toUpperCase());
        tvPressure.setText("Pressure: " + String.valueOf(weatherDetails.getPressure()) + " hPa");
        tvWindSpeed.setText("Wind: " + String.valueOf(windSpeed) + " km/h");
        tvMinTemperature.setText("Min: " + String.valueOf(weatherDetails.getMinTemp()) + "°C");
        tvMaxTemperature.setText("Max: " + String.valueOf(weatherDetails.getMaxTemp()) + "°C");
        ivCurrentWeatherState.setImageResource(WeatherIconSelector.getIcon(weatherDescription.getIcon()));
        tvWeatherUpdate.setText("Last updated: Just now");

        showCurrentWeatherLayout();
    }

    @Override
    public void refreshForecastWeather(List<Forecast> forecasts) {
        forecastAdapter.refreshForecast(forecasts);
    }

    @Override
    public void showSearchBox() {
        tvToolbarTitle.setVisibility(View.GONE);
        etSearchLocation.setVisibility(View.VISIBLE);
        etSearchLocation.requestFocus();
        editTextFocused = true;
        invalidateOptionsMenu();
        showKeyboard();
    }

    @Override
    public void hideSearchBox() {
        etSearchLocation.setVisibility(View.GONE);
        tvToolbarTitle.setVisibility(View.VISIBLE);
        editTextFocused = false;
        invalidateOptionsMenu();
        hideKeyboard();
    }

    @OnClick(R.id.btn_manage_settings)
    public void openSettingsActivity(){

    }

    @OnClick(R.id.rl_current_weather)
    public void onCurrentWeatherClicked() {
        showToast("Details will open!");
    }

    private void setViewAnimatorAnimations(Context context, ViewAnimator va) {
        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
        va.setInAnimation(in);
        va.setOutAnimation(out);
    }

    private void showCurrentWeatherLayout(){
        viewAnimator.setChild(R.id.ll_current_weather);
    }

    private void showKeyboard() {
        final InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(etSearchLocation, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearchLocation.getWindowToken(), 0);
    }

    private boolean isTextInputValid() {
        textInput = etSearchLocation.getText().toString().trim();
        if (textInput.isEmpty()) {
            showToast(R.string.msg_text_input_empty);
            return false;
        } else {
            return true;
        }
    }
}