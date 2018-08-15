package com.jwhh.notekeeper


import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log


class SettingsActivity1Fragment : PreferenceFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
		
		Log.d("test", "FRAGMENT IS IN VIEW")
    }
}
