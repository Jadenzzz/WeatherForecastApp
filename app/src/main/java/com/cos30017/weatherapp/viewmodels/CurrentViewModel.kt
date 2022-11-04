package com.cos30017.weatherapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.cos30017.weatherapp.WeatherApplication

import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.utils.Credentials
import com.cos30017.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class CurrentViewModel(
    app: Application,
    val currentRepository: CurrentWeatherRepository
) : AndroidViewModel(app)
{
    private var city:String = "Melbourne";
    val currentWeather: MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()
    var currentResponse: CurrentWeatherResponse? = null


    init {
        getCurretnWeather(city)
    }

    fun getCurretnWeather(city: String) = viewModelScope.launch {
        safeSearchCurrentCall(city)
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

    private fun handleCurrentWeatherResponse(response: Response<CurrentWeatherResponse>) : Resource<CurrentWeatherResponse> {
        //return response if the request is success
        if(response.isSuccessful) {

            response.body()?.let { resultResponse ->

                    currentResponse = resultResponse

                return Resource.Success(currentResponse ?: resultResponse)
            }
        }
        Log.i("RESPONSE", "fAIL")
        return Resource.Error(response.message())
    }

    private suspend fun safeSearchCurrentCall(city: String) {
        //post value to resource loading state
        currentWeather.postValue(Resource.Loading())
        Log.v("check", currentWeather.value.toString())
        try {
            if(hasInternetConnection()) {
                val response = currentRepository.getCurrent(city)
                Log.v("checkre", response.body().toString())
                currentWeather.postValue(handleCurrentWeatherResponse(response))//if connection is healthy then post response to handle function

            } else {
                currentWeather.postValue(Resource.Error("No internet connection"))// if there is connection error then post value to error class of resource
            }
        } catch(t: Throwable) {
            when(t) {
                //Other error
                is IOException -> currentWeather.postValue(Resource.Error("Network Failure"))
                else -> {currentWeather.postValue(Resource.Error("Conversion Error"))
                    Log.v("check", currentWeather.value.toString())}
            }
        }
    }

}
