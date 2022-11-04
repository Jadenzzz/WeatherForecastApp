package com.cos30017.weatherapp.ui.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.activity.MainActivity
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.utils.Resource
import com.cos30017.weatherapp.viewmodels.CurrentViewModel
import com.squareup.picasso.Picasso
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class HomeFragment : Fragment() {

    lateinit var weather : CurrentWeatherModel
    lateinit var viewModel : CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val location = view.findViewById<TextView>(R.id.city)
        val temp = view.findViewById<TextView>(R.id.temp)
        val icon = view.findViewById<ImageView>(R.id.weather_icon)
        val humidity = view.findViewById<TextView>(R.id.humidity)
        val windsp = view.findViewById<TextView>(R.id.windspeed)
        val desc = view.findViewById<TextView>(R.id.desc)

        viewModel = (activity as MainActivity).viewModel
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                //if the connection is success
                is Resource.Success -> {
                    weather = viewModel.currentWeather.value?.data?.current!!
                    weather.location = viewModel.currentWeather.value?.data?.location?.name
                    weather.weatherIcons = viewModel.currentWeather.value?.data?.current?.weatherIcons!!
                    temp.text = weather.temperature.toString() + "\u2103"
                    location.text = weather.location.toString()
                    desc.text = "Condition: " + weather.weatherDescriptions.joinToString()
                    windsp.text = "Wind Speed: " + weather.windSpeed.toString()
                    humidity.text = "Humidity: " + weather.humidity.toString()
                    weather.weatherIcons?.let { DrawWeatherIcon(it.joinToString("//"),icon) }
                }
                //if there is an error with the connection
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                //do nothing when resource is in loading state
                is Resource.Loading -> {
                }
            }
        })


        super.onViewCreated(view, savedInstanceState)

    }

    private fun DrawWeatherIcon(url: String, icon: ImageView)
    {
        Picasso.with(context).load(url).into(icon) //Draw Image from url by Picasso
    }
}