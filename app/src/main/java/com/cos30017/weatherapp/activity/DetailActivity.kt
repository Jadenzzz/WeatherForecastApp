package com.cos30017.weatherapp.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        forecast?.let{
            vHourList = findViewById(R.id.hour_recycler)
            linearLayoutManager = LinearLayoutManager(this)
            //apply the layout manager
            vHourList.layoutManager = linearLayoutManager
            //create an adapter
            adapter = HourAdapter(forecast.hour) {}
            //apply the adapter for the recycler view
            vHourList.adapter = adapter
        }
    }
}