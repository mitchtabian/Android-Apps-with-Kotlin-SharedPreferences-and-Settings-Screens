package com.jwhh.notekeeper


import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.util.Log


class SettingsActivity1Fragment : PreferenceFragment(),
        Preference.OnPreferenceClickListener
{

    val TAG = "Settings1Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main)

        // Set Preference Click Listener
        val uploadWifiPreference: Preference = preferenceManager.findPreference(getString(R.string.key_upload_over_wifi))
        uploadWifiPreference.onPreferenceClickListener = this
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when(preference!!.key){
            getString(R.string.key_upload_over_wifi) -> {
                printToLog("Change detected to preference. key: " + getString(R.string.key_upload_over_wifi))
            }
        }

        return true
    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
}
