package com.jwhh.notekeeper


import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log


class SettingsActivity1Fragment : PreferenceFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main)
    }
}
