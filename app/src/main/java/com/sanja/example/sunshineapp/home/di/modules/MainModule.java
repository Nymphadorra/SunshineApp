package com.sanja.example.sunshineapp.home.di.modules;

import android.content.Context;

import com.sanja.example.sunshineapp.home.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public Context provideAppContext() {
        return context;
    }
}