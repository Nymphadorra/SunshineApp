package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.home.core.api.APIService;
import com.sanja.example.sunshineapp.home.core.api.mvp.AbstractPresenter;

public class HomePresenter extends AbstractPresenter<HomeMVP.View> implements HomeMVP.Presenter{
    private final APIService apiService;

    public HomePresenter(APIService apiService) {
        this.apiService = apiService;
    }
}