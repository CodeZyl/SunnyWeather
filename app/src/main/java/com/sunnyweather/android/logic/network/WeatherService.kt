package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.DailyResponse
import com.sunnyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    //https://api.caiyunapp.com/v2.5/{Token}/{经度, 纬度}/realtime.json

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng")lng : String , @Path("lat")lat : String) : Call<RealtimeResponse>

    //https://api.caiyunapp.com/v2.5/{Token}/{经度, 纬度}/weather.json
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/weather.json")
    fun getDailyWeather(@Path("lng")lng : String , @Path("lat")lat : String) : Call<DailyResponse>
}