package com.sanja.example.sunshineapp.di.components;

import com.sanja.example.sunshineapp.core.api.APIService;
import com.sanja.example.sunshineapp.di.modules.APIModule;
import com.sanja.example.sunshineapp.di.modules.HomeModule;
import com.sanja.example.sunshineapp.di.modules.MainModule;
import com.sanja.example.sunshineapp.di.scopes.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {MainModule.class, HomeModule.class, APIModule.class})
public interface AppComponent {
    //We need to explicitly say what dependencies will be accessible to subcomponents.

    APIService apiService();
}