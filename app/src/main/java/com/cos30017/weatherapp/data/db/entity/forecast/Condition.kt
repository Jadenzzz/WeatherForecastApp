package com.cos30017.weatherapp.data.db.entity.forecast


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
):Parcelable{}