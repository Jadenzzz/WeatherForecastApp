package com.cos30017.weatherapp.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.network.response.CurrentWeatherResponse
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.model.ForecastModel
import com.cos30017.weatherapp.repository.CurrentWeatherRepository
import com.cos30017.weatherapp.viewmodels.CurrentViewModel
import com.cos30017.weatherapp.viewmodels.ForecastViewModel


class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val forecastViewModel: ForecastViewModel by activityViewModels()

    lateinit var forecast : List<ForecastModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    private fun getForecast() {
        forecastViewModel.forecast.observe(viewLifecycleOwner,
            { t ->
                if (t != null) {
                    forecast = t
                }
            })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getForecast()
        val fav = view.findViewById<TextView>(R.id.fav)
        fav.text = forecastViewModel.forecast.value?.get(0)?.date.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastViewModel.cancelJobs()
    }
}