package com.cos30017.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cos30017.weatherapp.api.current.RetrofitBuilder
import com.cos30017.weatherapp.api.forecast.RetrofitBuilderForecast
import com.cos30017.weatherapp.utils.Credentials
import kotlinx.coroutines.*
import java.io.IOException

class ForecastRepository
 {
    //get forecast data from api
    suspend fun getForecast(city: String) =
        RetrofitBuilderForecast.apiService.getForecast(Credentials.API_KEY_FORECAST,city)
}