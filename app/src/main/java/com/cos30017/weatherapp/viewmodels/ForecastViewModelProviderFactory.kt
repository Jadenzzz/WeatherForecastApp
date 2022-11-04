package com.cos30017.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.repository.ForecastRepository

class ForecastViewModelProviderFactory(
    val app: Application,
    val forecastRepository: ForecastRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForecastViewModel(app, forecastRepository) as T
    }

}