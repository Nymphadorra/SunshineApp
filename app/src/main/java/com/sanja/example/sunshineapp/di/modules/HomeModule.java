package com.sanja.example.sunshineapp.di.modules;

import android.content.SharedPreferences;

import com.sanja.example.sunshineapp.WeatherManager;
import com.sanja.example.sunshineapp.core.api.APIService;
import com.sanja.example.sunshineapp.home.HomeMVP;
import com.sanja.example.sunshineapp.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    @Provides
    public HomeMVP.Presenter provideHomePresenter(APIService apiService, WeatherManager weatherManager) {
        return new HomePresenter(apiService, weatherManager);
    }
}