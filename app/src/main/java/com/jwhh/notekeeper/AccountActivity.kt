package com.jwhh.notekeeper

import android.content.SharedPreferences
import android.os.*
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.layout_account_toolbar.*
import java.util.*


class AccountActivity : AppCompatActivity(),
        View.OnClickListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        input_phone_number.addTextChangedListener(PhoneNumberFormattingTextWatcher(Locale.getDefault().country))

        initToolbar()
        initWidgetValues()
    }

    private fun initWidgetValues(){
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val name = prefs.getString(PREFERENCES_NAME, "Mitch Tabian")
        val username = prefs.getString(PREFERENCES_USERNAME, "Mitch.Tabian")
        val email = prefs.getString(PREFERENCES_EMAIL, "mitch@tabian.ca")
        val phoneNum = prefs.getString(PREFERENCES_PHONE_NUMBER, "1 604 855-1111")
        val gender = prefs.getString(PREFERENCES_GENDER, "Male")

        Log.d("name", name)

        input_name.setText(name)
        input_username.setText(username)
        input_email_address.setText(email)
        input_phone_number.setText(phoneNum)

        val genders = resources.getStringArray(R.array.gender_array)
        for (index in 0.. genders.size){
            if(genders.get(index).equals(gender)){
                gender_spinner.setSelection(index)
                break
            }
        }
    }

    override fun onClick(widget: View?) {
        when(widget?.id){

            R.id.close -> finish()

        }

    }

	fun hideKeyboard() {
        printToLog("closing keyboard")
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.rootView.windowToken, 0)
    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
	
    private fun initToolbar() {
        close.setOnClickListener(this)
        save.setOnClickListener(this)
    }

}