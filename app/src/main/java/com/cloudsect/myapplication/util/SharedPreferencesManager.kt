package com.cloudsect.myapplication.util

/*
    Created By Divyanshu Kumar Kushwaha on 26-August 2023
 */

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {


    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        val defaultValue = 0
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun saveFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        val defaultValue = 0.0f
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        val defaultValue =""
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
