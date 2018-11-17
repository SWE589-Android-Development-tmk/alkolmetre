package com.example.mk0730.alkolmetre;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.filter_pref_alkolmetre);
        addPreferencesFromResource(R.xml.order_pref_alkolmetre);
    }
}
