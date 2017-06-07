package com.sanja.example.sunshineapp.di.modules;

import com.sanja.example.sunshineapp.WeatherManager;
import com.sanja.example.sunshineapp.settings.SettingsMVP;
import com.sanja.example.sunshineapp.settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {
    @Provides
    public SettingsMVP.Presenter provideSettingsPresenter(WeatherManager weatherManager){
        return new SettingsPresenter(weatherManager);
    }
}
