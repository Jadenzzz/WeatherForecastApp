package com.cos30017.weatherapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cos30017.weatherapp.model.CurrentWeatherModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(current: CurrentWeatherModel)

    @Query("SELECT * FROM current")
    fun getCurrent(): LiveData<CurrentWeatherModel>

}