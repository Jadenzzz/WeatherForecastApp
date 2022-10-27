package com.cos30017.weatherapp.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cos30017.weatherapp.data.db.dao.CurrentDAO
import com.cos30017.weatherapp.model.CurrentWeatherModel

@Database(entities = [CurrentWeatherModel::class], version = 1)
abstract class CurrentDatabase : RoomDatabase() {

    abstract fun currentDao() : CurrentDAO
}