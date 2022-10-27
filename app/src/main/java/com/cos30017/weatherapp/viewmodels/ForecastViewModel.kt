package com.cos30017.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.model.ForecastModel
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.repository.ForecastRepository

class ForecastViewModel : ViewModel() {
    private var city:String = "Melbourne";

    var forecast : LiveData<List<ForecastModel>> = ForecastRepository.getForecast(city)

    fun cancelJobs() {
        ForecastRepository.cancelJob()
    }
}