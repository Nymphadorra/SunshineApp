package com.sanja.example.sunshineapp.home;

import com.sanja.example.sunshineapp.home.core.api.mvp.BasePresenter;

public interface HomeMVP {
    interface View {
    }

    interface Presenter extends BasePresenter<View> {
    }
}