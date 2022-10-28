package com.cos30017.weatherapp.data.db.database

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromList(icon: List<String>):String{
        return icon.joinToString("//")
    }

    @TypeConverter
    fun toList(icon: String):List<String>{
        var list : List<String>
        list = icon.split("//")
        return list
    }
}