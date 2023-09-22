package com.example.homeworksavestatewithsharedpreferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MyApplication : Application() {
    private lateinit var sharedPref: SharedPreferences
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()

        instance = this
        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun saveData(numberOfClicks:Int) {
        val numberToSave = gson.toJson(numberOfClicks)
        sharedPref.edit().putString("NumberOfClicks", numberToSave).apply()
    }

    fun getSavedData():Int {
        val jsonNumber = sharedPref.getString("NumberOfClicks", "")
        var result = 0

        if (jsonNumber?.isNotEmpty() == true) {
            val type: Type = Int::class.java
            result = gson.fromJson(jsonNumber, type)
        }

        return result
    }

    companion object {
        private lateinit var instance:MyApplication
        fun getApp() = instance
    }
}
