package com.cos30017.weatherapp.data.db.database

import android.content.Context
import androidx.room.*
import com.cos30017.weatherapp.data.db.dao.CurrentDAO
import com.cos30017.weatherapp.model.CurrentWeatherModel

@Database(entities = [CurrentWeatherModel::class], version = 1)
@TypeConverters(Converter::class)
abstract class CurrentDatabase : RoomDatabase() {

    abstract fun getCurrentDao() : CurrentDAO


    companion object {
        @Volatile
        private var instance: CurrentDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CurrentDatabase::class.java,
                "current"
            ).build()
    }
}