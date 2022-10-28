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

//    private fun getWeather() {
//        currentViewModel.currentWeather.observe(viewLifecycleOwner, Observer { response ->
//            when(response) {
//                is Resource.Success -> {
//                    weather = response.data?.current!!
//                }
//                is Resource.Error -> {
//                    response.message?.let { message ->
//                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
//                    }
//                }
//                is Resource.Loading -> {
//                }
//            }
//        })
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val location = view.findViewById<TextView>(R.id.city)
        val textbox = view.findViewById<TextView>(R.id.temp)
        val icon = view.findViewById<ImageView>(R.id.weather_icon)
        viewModel = (activity as MainActivity).viewModel
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    textbox.text = viewModel.currentWeather.value?.data?.current?.temperature.toString()
                    location.text = viewModel.currentWeather.value?.data?.location.toString()
                    viewModel.currentWeather.value?.data?.current?.weatherIcons?.let { DrawWeatherIcon(it.joinToString("//"),icon) }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)

    }

    private fun DrawWeatherIcon(url: String, icon: ImageView)
    {
        Picasso.with(context).load(url).into(icon)
    }



}