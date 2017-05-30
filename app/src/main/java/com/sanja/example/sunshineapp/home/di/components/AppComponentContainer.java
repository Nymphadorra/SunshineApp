package com.sanja.example.sunshineapp.home.di.components;

import android.app.Application;

import com.sanja.example.sunshineapp.home.di.modules.MainModule;

public class AppComponentContainer {
    private static final String INITIALIZE_EXCEPTION =
            "You must initialize AppComponent in order to use get() method";
    private static AppComponent appComponent;

    public static AppComponent get() {
        if (appComponent == null) {
            throw new IllegalStateException(INITIALIZE_EXCEPTION);
        }
        return appComponent;
    }

    public static void init(Application app) {
        appComponent = DaggerAppComponent.builder()
                //Modules with parameters must be explicitly created.
                .mainModule(new MainModule(app))
                .build();
    }
}