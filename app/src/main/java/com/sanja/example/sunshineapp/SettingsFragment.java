package com.sanja.example.sunshineapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

import com.sanja.example.sunshineapp.di.components.AppComponent;
import com.sanja.example.sunshineapp.di.components.AppComponentContainer;
import com.sanja.example.sunshineapp.di.components.DaggerSettingsComponent;
import com.sanja.example.sunshineapp.di.components.SettingsComponent;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    @Inject WeatherManager weatherManager;
     private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppComponentContainer.get());
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);

        sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference) && !(p instanceof NumberPickerPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            } else if (p instanceof NumberPickerPreference){
                int value = sharedPreferences.getInt(p.getKey(), 10);
                setPreferenceSummary(p, value);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        weatherManager.isChangeMade(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_key_default_location))) {
            Preference pref = findPreference(key);
            // Set summary to be the user-description for the selected value
            pref.setSummary(sharedPreferences.getString(key, ""));
        } else if (key.equals(getString(R.string.pref_key_unit))) {
            Preference pref = findPreference(key);
            pref.setSummary(sharedPreferences.getString(key, ""));
        } else if (key.equals(getString(R.string.pref_key_forecast_count))) {
            Preference pref = findPreference(key);
            pref.setSummary(Integer.toString(sharedPreferences.getInt(key, 10)));
        }
        weatherManager.isChangeMade(true);
    }

    private void injectDependencies(AppComponent appComponent) {
        SettingsComponent settingsComponent = DaggerSettingsComponent.builder()
                .appComponent(appComponent)
                .build();
        settingsComponent.inject(this);
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }
}