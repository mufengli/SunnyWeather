package com.sunnyweather.android.logic.network

import kotlinx.coroutines.supervisorScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService=ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query:String)=placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine{
                coroutine -> enqueue(object :Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body=response.body()
                if(body!=null)coroutine.resume(body)
                else  coroutine.resumeWithException(RuntimeException("response is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                coroutine.resumeWithException(t)
            }
        })
        }
    }
}