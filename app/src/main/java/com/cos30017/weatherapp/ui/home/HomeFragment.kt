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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.model.CurrentWeatherModel
import com.cos30017.weatherapp.viewmodels.CurrentViewModel
import com.squareup.picasso.Picasso
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class HomeFragment : Fragment() {



    private val currentViewModel: CurrentViewModel by activityViewModels()

    lateinit var weather : CurrentWeatherModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getWeather() {
        currentViewModel.weather.observe(viewLifecycleOwner,
            { t ->
                if (t != null) {
                    weather = t
                }
            })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getWeather()
        val location = view.findViewById<TextView>(R.id.city)
        val textbox = view.findViewById<TextView>(R.id.temp)
        val icon = view.findViewById<ImageView>(R.id.weather_icon)

        textbox.text = currentViewModel.weather.value?.temperature.toString()
        location.text = currentViewModel.weather.value?.location?.toString()
        currentViewModel.weather.value?.weatherIcons?.joinToString("//")
            ?.let { DrawWeatherIcon(it, icon) }
        super.onViewCreated(view, savedInstanceState)

    }

    private fun DrawWeatherIcon(url: String, icon: ImageView)
    {
        Picasso.with(context).load(url).into(icon)
    }

    override fun onDestroy() {
        super.onDestroy()
        currentViewModel.cancelJobs()
    }
}