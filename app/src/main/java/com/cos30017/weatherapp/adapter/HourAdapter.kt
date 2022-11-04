package com.cos30017.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cos30017.weatherapp.R
import com.cos30017.weatherapp.data.db.entity.forecast.Hour
import com.squareup.picasso.Picasso

class HourAdapter(private var hourList: List<Hour>,
                  private val listener: (Hour) -> Unit): RecyclerView.Adapter<HourAdapter.HourViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourAdapter.HourViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.hour_list_row, parent, false) as View
        return HourViewHolder(view)
    }
    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.bind(hourList[position])
    }

    //get the size of list
    override fun getItemCount(): Int {
        Log.i("sizeC",hourList.size.toString())
        return hourList.size
    }

    //change list of data
    fun setList(list: List<Hour>) {
        hourList = list
        Log.i("size",list.size.toString())
        notifyDataSetChanged()
    }

    inner class HourViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        //specify all item in the layout
        val conditionIcon = v.findViewById<ImageView>(R.id.condition)
        val min = v.findViewById<TextView>(R.id.minTemp)
        val max = v.findViewById<TextView>(R.id.maxTemp)
        val hour = v.findViewById<TextView>(R.id.hour_list)

        //bind data
        fun bind(item: Hour) {

            Picasso.with(itemView.context).load("http:"+item.condition.icon).into(conditionIcon)
            min.text = "Humidity: " + item.humidity.toString()
            max.text = "Temp: " + item.tempC.toString()
            hour.text = item.time


        }
    }
}