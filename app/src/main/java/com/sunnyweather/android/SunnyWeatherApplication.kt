package com.sunnyweather.android

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SunnyWeatherApplication : Application() {
    companion object {
        lateinit var context : Context
        const val TOKEN = "Yw5zDmEOOQgehqTp"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}