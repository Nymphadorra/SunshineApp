package com.sanja.example.sunshineapp.di.components;

import com.sanja.example.sunshineapp.di.modules.SettingsModule;
import com.sanja.example.sunshineapp.di.scopes.ActivityScope;
import com.sanja.example.sunshineapp.settings.SettingsActivity;

import dagger.Component;

@ActivityScope
@Component(
        modules = SettingsModule.class,
        dependencies = AppComponent.class)
public interface SettingsComponent {
    void inject(SettingsActivity settingsActivity);
}