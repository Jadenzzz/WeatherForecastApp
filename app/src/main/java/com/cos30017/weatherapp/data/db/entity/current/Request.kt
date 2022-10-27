package com.cos30017.weatherapp.data.db.entity.current


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
):Parcelable{}