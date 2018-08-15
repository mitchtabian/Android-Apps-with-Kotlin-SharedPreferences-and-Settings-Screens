package com.jwhh.notekeeper


import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.*
import android.text.TextUtils
import android.util.Log


class SettingsActivity1Fragment : PreferenceFragment(),
        Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener
{

    val TAG = "Settings1Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.pref_main)

        // Set Preference Click Listener
        val uploadWifiPreference: Preference = preferenceManager.findPreference(getString(R.string.key_upload_over_wifi))
        uploadWifiPreference.onPreferenceClickListener = this

        // set Preference Change Listeners
        val galleryNamePreference: Preference = preferenceManager.findPreference(getString(R.string.key_gallery_name))
        galleryNamePreference.onPreferenceChangeListener = this

        uploadWifiPreference.onPreferenceChangeListener = this

        val notificationsNewMessagePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message))
        notificationsNewMessagePreference.onPreferenceChangeListener = this

        val notificationsRingtonePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message_ringtone))
        notificationsRingtonePreference.onPreferenceChangeListener = this


        val vibratePreference: Preference = preferenceManager.findPreference(getString(R.string.key_vibrate))
        vibratePreference.onPreferenceChangeListener = this

        val backupFrequencyPreference: Preference = preferenceManager.findPreference(getString(R.string.key_backup_frequency))
        backupFrequencyPreference.onPreferenceChangeListener = this


        // set the summaries to show the most recent information
        updateSummary(galleryNamePreference, null)
        updateSummary(notificationsRingtonePreference, null)
        updateSummary(backupFrequencyPreference, null)
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when(preference!!.key){
            getString(R.string.key_upload_over_wifi) -> {
                printToLog("Change detected to preference. key: " + getString(R.string.key_upload_over_wifi))
            }
        }

        return true
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        printToLog("Preference change detected")

        // Update the summary value showing on the screen
        updateSummary(preference, newValue)

        // If this was a real application we would send the updates to server here
        uploadPreferencesToServer()

        printToLog("successfully updated preference. key: " + preference!!.key)

        return true // Update the state of the preference with the new value
    }

    private fun uploadPreferencesToServer(){
        // Code for uploading updated preferences to server
    }

    private fun updateSummary(preference: Preference?, newValue: Any?){

        var stringValue = ""
        if(newValue != null){
            stringValue = newValue.toString()
        }
        else{
            stringValue = PreferenceManager
                    .getDefaultSharedPreferences(preference!!.context)
                    .getString(preference.key, "").toString()
        }

        if (preference is ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            val listPreference = preference
            val index = listPreference.findIndexOfValue(stringValue)

            // Set the summary to reflect the new value.
            preference.setSummary(
                    if (index >= 0)
                        listPreference.entries[index]
                    else
                        null)

        } else if (preference is RingtonePreference) {
            // For ringtone preferences, look up the correct display value
            // using RingtoneManager.
            if (TextUtils.isEmpty(stringValue)) {
                // Empty values correspond to 'silent' (no ringtone).
                preference.setSummary(R.string.pref_ringtone_silent)

            } else {
                val ringtone = RingtoneManager.getRingtone(
                        preference.getContext(), Uri.parse(stringValue))

                if (ringtone == null) {
                    // Clear the summary if there was a lookup error.
                    preference.setSummary(null)
                } else {
                    // Set the summary to reflect the new ringtone display
                    // name.
                    val name = ringtone.getTitle(preference.getContext())
                    preference.setSummary(name)
                }
            }

        }
        else if(preference is EditTextPreference){
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.summary = stringValue
        }
    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
}
