package com.sanja.example.sunshineapp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sanja.example.sunshineapp.BaseActivity;
import com.sanja.example.sunshineapp.R;
import com.sanja.example.sunshineapp.SettingsFragment;
import com.sanja.example.sunshineapp.di.components.AppComponent;
import com.sanja.example.sunshineapp.di.components.DaggerSettingsComponent;
import com.sanja.example.sunshineapp.di.components.SettingsComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements
        SettingsMVP.View{
    @Inject SettingsMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setupToolbar(toolbar, R.string.toolbar_title_settings, true);

        getFragmentManager().beginTransaction()
                .replace(R.id.settings_fragment, new SettingsFragment())
                .commit();

        presenter.bind(this);
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        SettingsComponent settingsComponent = DaggerSettingsComponent.builder()
                .appComponent(appComponent)
                .build();
        settingsComponent.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                presenter.onBackClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setIntentResult() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
    }
}
