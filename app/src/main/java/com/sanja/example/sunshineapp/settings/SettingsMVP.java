package com.sanja.example.sunshineapp.settings;

import com.sanja.example.sunshineapp.core.mvp.BasePresenter;

public interface SettingsMVP {
    interface View{
        void finish();

        void setIntentResult(boolean isChangeMade);
    }

    interface Presenter extends BasePresenter<View>{
        void onBackClicked();
    }
}