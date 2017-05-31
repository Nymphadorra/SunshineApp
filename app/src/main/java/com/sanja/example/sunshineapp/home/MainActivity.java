package com.sanja.example.sunshineapp.home;

import android.os.Bundle;

import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.home.di.components.AppComponent;
import com.sanja.example.sunshineapp.home.di.components.DaggerHomeComponent;
import com.sanja.example.sunshineapp.home.di.components.HomeComponent;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HomeMVP.View{
    @Inject HomeMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BRENČAJ SE!!!
        presenter.bind(this);
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .build();
        homeComponent.inject(this);
    }
}