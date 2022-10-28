package com.cos30017.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cos30017.weatherapp.repository.CurrentWeatherRepository

//@J
//class ViewModelProviderFactory (
//    val app: Application,
//    val currentRepository: CurrentWeatherRepository
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return CurrentViewModel(app, currentRepository) as T
//    }
//}
class ViewModelProviderFactory(
    val app: Application,
    val currentRepository: CurrentWeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentViewModel(app, currentRepository) as T
    }

}