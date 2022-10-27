package com.cos30017.weatherapp.data.db.network.response


import com.cos30017.weatherapp.data.db.entity.current.Current
import com.cos30017.weatherapp.data.db.entity.current.Request
import com.cos30017.weatherapp.data.db.entity.current.Location
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val request: Request,
    var location: Location,
    @SerializedName("current")
    @Expose
    var  current: CurrentWeatherModel
)