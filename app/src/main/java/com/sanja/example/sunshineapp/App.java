package com.sanja.example.sunshineapp;

import android.app.Application;

import com.sanja.example.sunshineapp.di.components.AppComponentContainer;

import timber.log.BuildConfig;
import timber.log.Timber;

public class App extends Application {

    //Called when app is created. Here are all app wide initializations.
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppComponentContainer.init(this);
    }
}