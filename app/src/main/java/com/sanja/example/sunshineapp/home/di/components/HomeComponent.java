package com.sanja.example.sunshineapp.home.di.components;

import com.sanja.example.sunshineapp.home.MainActivity;
import com.sanja.example.sunshineapp.home.di.modules.HomeModule;
import com.sanja.example.sunshineapp.home.di.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        modules = HomeModule.class,
        dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(MainActivity mainActivity);
}