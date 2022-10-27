package com.cos30017.weatherapp.model

import android.os.Parcelable
import com.cos30017.weatherapp.data.db.entity.current.Request
import com.cos30017.weatherapp.data.db.entity.forecast.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ForecastModel (
    var location: Location?,
    var date: String,
    @SerializedName("date_epoch")
    var dateEpoch: Int,
    var day: Day,
    var astro: Astro,
    var hour: List<Hour>

    ): Parcelable {
        }