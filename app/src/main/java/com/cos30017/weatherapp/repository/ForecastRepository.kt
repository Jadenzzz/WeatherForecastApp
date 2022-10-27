package com.cos30017.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cos30017.weatherapp.api.forecast.RetrofitBuilderForecast
import com.cos30017.weatherapp.model.ForecastModel
import com.cos30017.weatherapp.utils.Credentials
import kotlinx.coroutines.*
import java.io.IOException

object ForecastRepository {

    var job: CompletableJob? = null
    //API Weather
    fun getForecast(city: String): LiveData<List<ForecastModel>> {
        job = Job()
        return object: LiveData<List<ForecastModel>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
                        throwable.printStackTrace()
                    }
                    CoroutineScope(Dispatchers.IO + theJob + coroutineExceptionHandler).launch {
                        val forecastResponse = RetrofitBuilderForecast.apiService.getForecast(Credentials.API_KEY_FORECAST, city)
                        Log.v("check", forecastResponse.body()?.forecast.toString())

                        withContext(Dispatchers.Main) {
                            if (forecastResponse.isSuccessful) {
                                value = forecastResponse.body()?.forecast?.forecastday

                                value?.forEach() {
                                    i->
                                    i.location = forecastResponse.body()?.location
                                }
                                Log.v("Response", "The call is successful")
                            } else {
                                try {
                                    Log.v("Tag", "Error ${forecastResponse.errorBody()}")
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