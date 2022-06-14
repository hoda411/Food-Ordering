package com.example.finalproject;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment1 extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}