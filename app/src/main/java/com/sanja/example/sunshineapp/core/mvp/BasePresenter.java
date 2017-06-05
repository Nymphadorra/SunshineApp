package com.sanja.example.sunshineapp.core.mvp;

public interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}