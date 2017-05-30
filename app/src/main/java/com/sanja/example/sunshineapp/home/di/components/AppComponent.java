package com.sanja.example.sunshineapp.home.di.components;

import com.sanja.example.sunshineapp.home.core.api.APIService;
import com.sanja.example.sunshineapp.home.di.modules.APIModule;
import com.sanja.example.sunshineapp.home.di.modules.MainModule;
import com.sanja.example.sunshineapp.home.di.scopes.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {MainModule.class, APIModule.class})
public interface AppComponent {
    //We need to explicitly say what dependencies will be accessible to subcomponents.

    APIService apiService();
}