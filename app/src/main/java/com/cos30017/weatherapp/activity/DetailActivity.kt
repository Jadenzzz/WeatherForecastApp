package com.cos30017.weatherapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.adapter.HourAdapter
import com.cos30017.weatherapp.model.ForecastModel


class DetailActivity: AppCompatActivity() {
    private lateinit var vHourList: RecyclerView
    private lateinit var adapter: HourAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        Log.i("ACTIVITY","Changed")
        val forecast = intent.getParcelableExtra<ForecastModel>("Forecast Model")

        val location = findViewById<TextView>(R.id.location_hour)
        val date = findViewById<TextView>(R.id.date)

        if (forecast != null) {
            location.text = forecast.location?.name
        };

        if (forecast != null) {
            date.text = forecast.date.toString()
        }
        forecast?.let{
            vHourList = findViewById(R.id.hour_recycler)
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            //apply the layout manager
            vHourList.layoutManager = linearLayoutManager
            //create an adapter
            adapter = HourAdapter(forecast.hour) {}
            //apply the adapter for the recycler view
            vHourList.adapter = adapter
        }
    }
}