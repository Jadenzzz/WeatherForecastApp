package com.cos30017.weatherapp.api

import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/current?access_key=7c335010ccc9639040be51bb98ea318e&query=Melbourne&lang=en
interface APIWeather {
    //query for getting current weather data
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("access_key") key:String,
        @Query("query") location: String,
        @Query("lang") languageCode: String = "en"
    ): Response<CurrentWeatherResponse>


    //query for getting forecast weather data
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key:String,
        @Query("q") location: String,
        @Query("days") days: String = "14",
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "no"
    ): Response<ForecastResponse>

}