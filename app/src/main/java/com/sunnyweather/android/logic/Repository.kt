package com.sunnyweather.android.logic


import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
/*
这段代码是一个 Kotlin object，名为 Repository。它包含一个名为 searchPlaces 的函数，
该函数接受一个字符串参数 query，并返回一个 LiveData 对象。在函数内部，它使用 Dispatchers.IO
来在后台线程中执行代码。它尝试使用 SunnyWeatherNetwork.searchPlaces 函数来搜索地点，如果响应状态为 "ok"，
则返回一个包含地点列表的 Result.success 对象，否则返回一个包含错误信息的 Result.failure 对象。如果发生异常，
则返回一个包含异常信息的 Result.failure 对象。最后，使用 emit 函数将结果发送到 LiveData 对象中。
 */
object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            }
            else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}