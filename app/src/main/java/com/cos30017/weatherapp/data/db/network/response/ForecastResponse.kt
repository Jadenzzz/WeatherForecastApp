package com.cos30017.weatherapp.data.db.network.response


import com.cos30017.weatherapp.data.db.entity.forecast.Current
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.entity.forecast.Location
import com.cos30017.weatherapp.model.ForecastModel

data class ForecastResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)