package com.cos30017.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.model.ForecastModel
import com.squareup.picasso.Picasso

class ForecastAdapter(private var forecastList: List<ForecastModel>,
                      private val listener: (ForecastModel) -> Unit): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.forecast_list, parent, false) as View
        return ForecastViewHolder(view)
    }
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        Log.i("sizeC",forecastList.size.toString())
        return forecastList.size
    }

    fun setList(list: List<ForecastModel>) {
        forecastList = list
        Log.i("size",list.size.toString())
        notifyDataSetChanged()
    }

    inner class ForecastViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val conditionIcon = v.findViewById<ImageView>(R.id.condition)
        val date = v.findViewById<TextView>(R.id.hour)
        val maxT = v.findViewById<TextView>(R.id.maxTemp)

        fun bind(item: ForecastModel) {

            Picasso.with(itemView.context).load("http:"+item.day.condition.icon).into(conditionIcon)
            date.text = item.date
            maxT.text = item.day.maxtempC.toString()

            v.setOnClickListener() {
                listener(item)
            }
        }
    }
}