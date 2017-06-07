package com.sanja.example.sunshineapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sanja.example.sunshineapp.di.components.AppComponent;
import com.sanja.example.sunshineapp.di.components.AppComponentContainer;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

    private TextView toolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppComponentContainer.get());
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    protected void setupToolbar(@NonNull Toolbar toolbar, @StringRes int toolbarTitleResId, boolean isHomeEnabled) {
        setSupportActionBar(toolbar);
        toolbarTitle = ButterKnife.findById(toolbar, R.id.tv_toolbar_title);
        setToolbarTitle(toolbarTitleResId);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(isHomeEnabled);
    }

    protected void setToolbarTitle(@StringRes int toolbarTitleResId) {
        toolbarTitle.setText(getString(toolbarTitleResId));
    }

    protected void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int stringResId) {
        showToast(getString(stringResId));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}