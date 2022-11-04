package com.cos30017.weatherapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.cos30017.weatherapp.WeatherApplication
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.model.ForecastModel
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.repository.ForecastRepository
import com.cos30017.weatherapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ForecastViewModel(
    app: Application,
    val forecastRepository: ForecastRepository
) : AndroidViewModel(app){

    private var city:String = "Melbourne";

    val forecast: MutableLiveData<Resource<ForecastResponse>> = MutableLiveData()
    var forecastResponse: ForecastResponse? = null

    //get forecast data whenever this class is initialized
    init {
        getForecast(city)
    }

    fun getForecast(city : String) = viewModelScope.launch {
        safeForecastCall(city)
    }

    //check internet connection
    private fun hasInternetConnection(): Boolean {
        //register activity with the connectivity manager
        val connectivityManager = getApplication<WeatherApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager // init connectivity manager

        //specify connectivity manager base on current build version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //init network object
            val network = connectivityManager.activeNetwork ?: return false //check if network is available
            //return the capabilities and an active network
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true//wifi check
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true//cellular check
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true//ethernet check
                else -> false
            }
        } else {
            //if the build version is below M
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true//wifi check
                    ConnectivityManager.TYPE_MOBILE -> true//cellular check
                    ConnectivityManager.TYPE_ETHERNET -> true//ethernet check
                    else -> false
                }
            }
        }
        return false
    }


    //Only retrieve data when the connection is success
    private suspend fun safeForecastCall(city: String) {
        forecast.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = forecastRepository.getForecast(city)
                Log.i("DEBUGG", response.body().toString())
                forecast.postValue(handleForecastResponse(response))
            } else {
                forecast.postValue(Resource.Error("No network connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> forecast.postValue(Resource.Error("Network Connection Failed"))
                else -> forecast.postValue(Resource.Error("Other Error"))
            }
        }
    }

    private fun handleForecastResponse(response: Response<ForecastResponse>) : Resource<ForecastResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                    forecastResponse = resultResponse

                return Resource.Success(forecastResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}