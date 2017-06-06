package com.sanja.example.sunshineapp.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sanja.example.sunshineapp.WeatherManager;
import com.sanja.example.sunshineapp.WeatherPreferences;
import com.sanja.example.sunshineapp.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @AppScope
    @Provides
    public WeatherPreferences provideWeatherPreferences(SharedPreferences sharedPreferences){
        return new WeatherPreferences(sharedPreferences);
    }

    @AppScope
    @Provides
    public WeatherManager provideWeatherManager(WeatherPreferences weatherPreferences){
        return new WeatherManager(weatherPreferences);
    }
}