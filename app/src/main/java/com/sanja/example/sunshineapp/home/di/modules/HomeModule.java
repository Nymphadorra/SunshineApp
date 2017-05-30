package com.sanja.example.sunshineapp.home.di.modules;

import com.sanja.example.sunshineapp.home.HomeMVP;
import com.sanja.example.sunshineapp.home.HomePresenter;
import com.sanja.example.sunshineapp.home.core.api.APIService;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    @Provides
    public HomeMVP.Presenter provideHomePresenter(APIService apiService){
        return new HomePresenter(apiService);
    }
}