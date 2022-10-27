//package com.cos30017.weatherapp.api.current
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RoomBuilder {
//    @Provides
//    @Singleton
//    fun provideRestaurantApi(retrofit: Retrofit): APIWeather =
//        retrofit.create(APIWeather::class.java)
//}