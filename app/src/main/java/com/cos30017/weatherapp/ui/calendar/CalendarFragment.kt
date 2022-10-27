package com.cos30017.weatherapp.ui.calendar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.activity.DetailActivity
import com.cos30017.weatherapp.adapter.ForecastAdapter
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.viewmodels.ForecastViewModel
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.entity.forecast.Forecastday
import com.cos30017.weatherapp.model.ForecastModel

class CalendarFragment : Fragment() {
    private val forecastViewModel: ForecastViewModel by activityViewModels()
    private lateinit var vForecastList: RecyclerView
    private var forecastList: List<ForecastModel> = listOf()
    private val forecastAdapter = ForecastAdapter(forecastList) {showDetails(it)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_calendar, container, false) as View
        vForecastList = view.findViewById(R.id.forecast_recyler_view)
        configureRecyclerView()
        return view
    }

    private fun configureRecyclerView(){
//        val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager = LinearLayoutManager(this.context)
        vForecastList.adapter = forecastAdapter
        vForecastList.layoutManager = linearLayoutManager


    }

    private fun getForecast() {
        forecastViewModel.forecast.observe(viewLifecycleOwner,
            { t ->
                if (t != null) {
                    forecastAdapter.setList(t)
                    Log.i("check", t.toString())
                }
            })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getForecast()
    }

    private fun showDetails(item: ForecastModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("Forecast Model", item)
        startActivity(intent)
    }


}