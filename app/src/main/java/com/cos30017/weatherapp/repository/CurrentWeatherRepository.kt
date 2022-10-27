package com.cos30017.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cos30017.weatherapp.api.current.RetrofitBuilder
import com.cos30017.weatherapp.data.db.entity.current.Location
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.utils.Credentials
import kotlinx.coroutines.*
import java.io.IOException

object CurrentWeatherRepository {

    var job: CompletableJob? = null
    //API Weather

    fun getCurrentWeather(city: String): LiveData<CurrentWeatherModel> {
        job = Job()
        return object: LiveData<CurrentWeatherModel>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
                        throwable.printStackTrace()
                    }
                    CoroutineScope(Dispatchers.IO + theJob + coroutineExceptionHandler).launch {
                        val currentResponse = RetrofitBuilder.apiService.getCurrentWeather(Credentials.API_KEY, city)
                        Log.i("check", currentResponse.body()?.current.toString())

                        withContext(Dispatchers.Main) {
                            if (currentResponse.isSuccessful) {
                                value = currentResponse.body()?.current
                                Log.i("check", currentResponse.body()?.current.toString())
                                value?.location = currentResponse.body()?.location?.name
                                Log.v("Response", "The call is successful hi")
                            } else {
                                try {
                                    Log.v("Tag", "Error ${currentResponse.errorBody()}")
                                } catch (e: IOException) {
                                    Log.v("ERROR", e.toString())
                                }
                            }
                            theJob.complete()
                        }
                    }
                }
            }
        }

    }

    fun cancelJob() {
        job?.cancel()
    }


}
