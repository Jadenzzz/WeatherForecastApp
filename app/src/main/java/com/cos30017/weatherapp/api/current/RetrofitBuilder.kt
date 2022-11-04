package com.cos30017.weatherapp.api.current

import com.cos30017.weatherapp.api.APIWeather
import com.cos30017.weatherapp.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object
    {
        //creating retrofit builder for current weather data
        val retrofitBuilder: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)//implement base url
                .addConverterFactory(GsonConverterFactory.create())//add gson converter
        }
        val apiService: APIWeather by lazy {
            retrofitBuilder
                .build()
                .create(APIWeather::class.java)// create api service
        }
    }

}