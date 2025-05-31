package com.example.fragmentexercise.helper

import android.content.Context

class SharedPrefManager(context: Context) {
    private val PREF_NAME = "onboarding_pref"
    private val KEY_FIRST_TIME = "isFirstTime"

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(KEY_FIRST_TIME, isFirstTime)
        editor.apply()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(KEY_FIRST_TIME, true)
    }
}