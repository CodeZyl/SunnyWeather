package com.sunnyweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place

object PlaceDao {
    fun savePlace(place:Place) {
        sharedPreferences().edit {
            putString("place" , Gson().toJson(place))
        }
    }
    fun getSavedPlace() : Place{
        val placeJosn = sharedPreferences().getString("place" , "")
        return Gson().fromJson(placeJosn , Place::class.java)
    }
    fun isPlaceSaved() : Boolean {
        return sharedPreferences().contains("place")
    }
    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather" , Context.MODE_PRIVATE)
}