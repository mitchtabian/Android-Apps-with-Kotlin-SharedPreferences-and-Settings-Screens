package com.jwhh.notekeeper


import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log


class SettingsActivity1Fragment : PreferenceFragment()
{

    val TAG = "Settings1Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main)
        
    }
    

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
}
