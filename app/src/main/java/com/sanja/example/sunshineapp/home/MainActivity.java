package com.sanja.example.sunshineapp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.home.di.components.AppComponent;

public class MainActivity extends BaseActivity implements HomeMVP.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BRENČAJ SE!!!
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {

    }
}