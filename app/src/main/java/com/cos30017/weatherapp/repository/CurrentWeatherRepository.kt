package com.cos30017.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.switchMap
import androidx.room.withTransaction
import com.cos30017.weatherapp.api.current.APIWeather
import com.cos30017.weatherapp.api.current.RetrofitBuilder
import com.cos30017.weatherapp.data.db.database.CurrentDatabase
import com.cos30017.weatherapp.data.db.entity.current.Location
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.utils.Credentials
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject



class CurrentWeatherRepository  (
    val db: CurrentDatabase
){

    suspend fun getCurrent(city: String) = RetrofitBuilder.apiService.getCurrentWeather(Credentials.API_KEY,city)


    suspend fun upsert(current: CurrentWeatherModel) = db.getCurrentDao().insertCurrentWeather(current)

    fun getSavedCurrent() = db.getCurrentDao().getCurrent()



}
