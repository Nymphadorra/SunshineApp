package com.sanja.example.sunshineapp.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sanja.example.sunshineapp.home.di.components.AppComponent;
import com.sanja.example.sunshineapp.home.di.components.AppComponentContainer;

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppComponentContainer.get());
    }

    protected abstract void injectDependencies(AppComponent appComponent);
}