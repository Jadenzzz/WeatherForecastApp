package com.cos30017.weatherapp.api.current

import com.cos30017.weatherapp.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val apiService: APIWeather by lazy {
        retrofitBuilder
            .build()
            .create(APIWeather::class.java)
    }
}