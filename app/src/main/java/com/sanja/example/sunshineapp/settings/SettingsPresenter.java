package com.sanja.example.sunshineapp.settings;

import com.sanja.example.sunshineapp.WeatherManager;
import com.sanja.example.sunshineapp.core.mvp.AbstractPresenter;

public class SettingsPresenter extends AbstractPresenter<SettingsMVP.View> implements SettingsMVP.Presenter {
    private final WeatherManager weatherManager;

    public SettingsPresenter(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    @Override
    public void onBackClicked() {
        view().setIntentResult(weatherManager.isChangeMade());
        view().finish();
    }
}