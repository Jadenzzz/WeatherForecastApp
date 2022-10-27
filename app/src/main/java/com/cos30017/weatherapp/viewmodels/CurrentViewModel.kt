package com.cos30017.weatherapp.viewmodels

import androidx.lifecycle.*
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.utils.Credentials

class CurrentViewModel : ViewModel() {
    private  val key = Credentials.API_KEY
    private var temperature: MutableLiveData<Int> = MutableLiveData()
    private var current:MutableLiveData<CurrentWeatherModel> = MutableLiveData()
    private var city:String = "Melbourne";


    val weather : LiveData<CurrentWeatherModel> = CurrentWeatherRepository.getCurrentWeather(city)

    fun cancelJobs() {
        CurrentWeatherRepository.cancelJob()
    }

}
