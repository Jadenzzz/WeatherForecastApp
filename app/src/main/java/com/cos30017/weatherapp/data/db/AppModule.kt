package com.cos30017.weatherapp.data.db

import android.app.Application
import androidx.room.Room
import com.cos30017.weatherapp.api.current.APIWeather
import com.cos30017.weatherapp.data.db.database.CurrentDatabase
import com.cos30017.weatherapp.utils.Credentials
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): APIWeather =
        retrofit.create(APIWeather::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : CurrentDatabase =
        Room.databaseBuilder(app, CurrentDatabase::class.java, "current_database")
            .build()
}