package com.sanja.example.sunshineapp.core.mvp;

import android.support.annotation.CallSuper;

public abstract class AbstractPresenter<V> implements BasePresenter<V> {

    private static final String BIND_EXCEPTION = "Cannot bind new view without unbinding the previous view first!";
    private static final String UNBIND_EXCEPTION = "Cannot unbind view which is not bound!";

    private volatile V view;

    @CallSuper
    @Override
    public void bind(V view) {
        if (this.view == null) {
            this.view = view;
            onBind();
        } else {
            throw new IllegalStateException(BIND_EXCEPTION);
        }
    }

    @CallSuper
    @Override
    public void unbind(V view) {
        if (this.view.equals(view)) {
            this.view = null;
            onUnbind();
        } else {
            throw new IllegalStateException(UNBIND_EXCEPTION);
        }
    }

    @Override
    public V view() {
        return view;
    }


    protected void onBind() {
    }

    protected void onUnbind() {
    }
}