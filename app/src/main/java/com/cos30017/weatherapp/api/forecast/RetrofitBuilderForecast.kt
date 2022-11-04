package com.cos30017.weatherapp.api.forecast

import com.cos30017.weatherapp.api.APIWeather
import com.cos30017.weatherapp.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilderForecast {
    //Creating retrofit builder for forecast data
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL_FORECAST)//implement base url of forecast data
            .addConverterFactory(GsonConverterFactory.create())// add gson converter
    }
    val apiService: APIWeather by lazy {
        retrofitBuilder
            .build()
            .create(APIWeather::class.java)// create api service
    }
}