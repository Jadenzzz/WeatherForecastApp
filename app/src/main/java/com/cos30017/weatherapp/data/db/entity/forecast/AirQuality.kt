package com.cos30017.weatherapp.data.db.entity.forecast


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AirQuality(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    @SerializedName("pm2_5")
    val pm25: Double,
    val pm10: Double,
    @SerializedName("us-epa-index")
    val usEpaIndex: Int,
    @SerializedName("gb-defra-index")
    val gbDefraIndex: Int
):Parcelable{}