package com.cos30017.weatherapp.repository

import com.cos30017.weatherapp.api.current.RetrofitBuilder
import com.cos30017.weatherapp.utils.Credentials


class CurrentWeatherRepository  (
){
    //get current weather from api
    suspend fun getCurrent(city: String) =
        RetrofitBuilder.apiService.getCurrentWeather(Credentials.API_KEY,city)

}
