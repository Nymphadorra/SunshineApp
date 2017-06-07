package com.sanja.example.sunshineapp.settings;

import android.os.Bundle;

import com.sanja.example.sunshineapp.BaseActivity;
import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.di.components.AppComponent;
import com.sanja.example.sunshineapp.di.components.DaggerSettingsComponent;
import com.sanja.example.sunshineapp.di.components.SettingsComponent;

import javax.inject.Inject;

public class SettingsActivity extends BaseActivity implements SettingsMVP.View{
    @Inject SettingsMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        SettingsComponent settingsComponent = DaggerSettingsComponent.builder()
                .appComponent(appComponent)
                .build();
        settingsComponent.inject(this);
    }
}
