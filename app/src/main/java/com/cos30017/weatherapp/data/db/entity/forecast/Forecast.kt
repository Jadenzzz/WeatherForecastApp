package com.cos30017.weatherapp.data.db.entity.forecast


import android.os.Parcelable
import com.cos30017.weatherapp.model.ForecastModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val forecastday: List<ForecastModel>
):Parcelable{}