package com.cos30017.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cos30017.weatherapp.data.db.entity.current.Location
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Entity(tableName = "current")
data class CurrentWeatherModel (
    @PrimaryKey
    @SerializedName("observation_time")
    var observationTime: String,
    var temperature: Int,
    @SerializedName("weather_code")
    var weatherCode: Int,
    @SerializedName("weather_icons")
    var weatherIcons: List<String>,
    @SerializedName("wind_speed")
    var windSpeed: Int,
    @SerializedName("wind_degree")
    var windDegree: Int,
    @SerializedName("wind_dir")
    var windDir: String,
    var pressure: Int,
    var humidity: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("uv_index")
    var uvIndex: Int,
    var visibility: Int,
    @SerializedName("is_day")
    var isDay: String,
    var location: String?,
    )
