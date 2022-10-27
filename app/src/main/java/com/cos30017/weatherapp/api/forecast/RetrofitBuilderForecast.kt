package com.cos30017.weatherapp.api.forecast

import com.cos30017.weatherapp.api.current.APIWeather
import com.cos30017.weatherapp.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilderForecast {
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL_FORECAST)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val apiService: APIWeather by lazy {
        retrofitBuilder
            .build()
            .create(APIWeather::class.java)
    }
}