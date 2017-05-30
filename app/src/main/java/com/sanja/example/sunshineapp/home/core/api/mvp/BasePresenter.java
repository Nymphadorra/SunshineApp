package com.sanja.example.sunshineapp.home.core.api.mvp;

public interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}