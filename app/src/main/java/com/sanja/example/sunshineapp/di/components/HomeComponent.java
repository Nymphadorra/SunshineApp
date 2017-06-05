package com.sanja.example.sunshineapp.di.components;

import com.sanja.example.sunshineapp.MainActivity;
import com.sanja.example.sunshineapp.di.modules.HomeModule;
import com.sanja.example.sunshineapp.di.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        modules = HomeModule.class,
        dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(MainActivity mainActivity);
}