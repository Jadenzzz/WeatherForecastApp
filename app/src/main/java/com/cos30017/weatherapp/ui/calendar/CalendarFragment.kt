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
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.activity.DetailActivity
import com.cos30017.weatherapp.activity.MainActivity
import com.cos30017.weatherapp.adapter.ForecastAdapter
import com.cos30017.weatherapp.data.db.network.response.ForecastResponse
import com.cos30017.weatherapp.viewmodels.ForecastViewModel
import com.cos30017.weatherapp.data.db.entity.forecast.Forecast
import com.cos30017.weatherapp.data.db.entity.forecast.Forecastday
import com.cos30017.weatherapp.model.ForecastModel
import com.cos30017.weatherapp.utils.Resource

class CalendarFragment : Fragment() {
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var vForecastList: RecyclerView
    private var forecastList: List<ForecastModel> = listOf()
    private var forecastAdapter = ForecastAdapter(forecastList) {showDetails(it)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_calendar, container, false) as View
        vForecastList = view.findViewById(R.id.forecast_recyler_view)
        configureRecyclerView()
        return view
    }

    //implement recyclerView
    private fun configureRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this.context)
        vForecastList.adapter = forecastAdapter
        vForecastList.layoutManager = linearLayoutManager


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel = (activity as MainActivity).forecastViewModel
        forecastViewModel.forecast.observe(viewLifecycleOwner, Observer { response ->
        when(response) {
            //if resource is success
            is Resource.Success -> {
                forecastList = forecastViewModel.forecast.value?.data?.forecast?.forecastday!!
                Log.i("CHECKRES", forecastViewModel.forecast.value?.data.toString())
                forecastAdapter.setList(forecastList)
            }
            //if there is an errorr with the connection
            is Resource.Error -> {
                response.message?.let { message ->
                    Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                }
            }
            //do nothing while in loading state
            is Resource.Loading -> {
            }
        }
    })
        configureRecyclerView()

    }
    //passing data to detail activity
    private fun showDetails(item: ForecastModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("Forecast Model", item)
        startActivity(intent)
    }


}