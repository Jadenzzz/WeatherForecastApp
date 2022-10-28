package com.cos30017.weatherapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.cos30017.weatherapp.WeatherApplication
import com.cos30017.weatherapp.data.db.database.CurrentDatabase

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

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<WeatherApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }


    private fun handleCurrentWeatherResponse(response: Response<CurrentWeatherResponse>) : Resource<CurrentWeatherResponse> {
        if(response.isSuccessful) {

            response.body()?.let { resultResponse ->
                if(currentResponse == null) {
                    Log.i("RESPONSE", "Success")
                    currentResponse = resultResponse
                } else {
                    Log.i("RESPONSE", "Fail")
                    var oldCurrent = currentResponse?.current
                    val newCurrent = resultResponse.current
                    oldCurrent = newCurrent
                }
                return Resource.Success(currentResponse ?: resultResponse)
            }
        }
        Log.i("RESPONSE", "fAIL")
        return Resource.Error(response.message())
    }

    private suspend fun safeSearchCurrentCall(city: String) {
        currentWeather.postValue(Resource.Loading())
        Log.v("check", currentWeather.value.toString())
        try {
            if(hasInternetConnection()) {
                val response = currentRepository.getCurrent(city)
                Log.v("checkre", response.body().toString())
                currentWeather.postValue(handleCurrentWeatherResponse(response))

            } else {
                currentWeather.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> currentWeather.postValue(Resource.Error("Network Failure"))
                else -> {currentWeather.postValue(Resource.Error("Conversion Error"))
                    Log.v("check", currentWeather.value.toString())}
            }
        }
    }

    fun getSavedCurrent() = currentRepository.getSavedCurrent()


    fun saveCurrent(current: CurrentWeatherModel) = viewModelScope.launch {
        currentRepository.upsert(current)
    }
}
