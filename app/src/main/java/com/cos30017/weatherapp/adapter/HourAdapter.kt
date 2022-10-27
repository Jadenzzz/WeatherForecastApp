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

    override fun getItemCount(): Int {
        Log.i("sizeC",hourList.size.toString())
        return hourList.size
    }

    fun setList(list: List<Hour>) {
        hourList = list
        Log.i("size",list.size.toString())
        notifyDataSetChanged()
    }

    inner class HourViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val conditionIcon = v.findViewById<ImageView>(R.id.condition)
        val time = v.findViewById<TextView>(R.id.hour)
        val avgT = v.findViewById<TextView>(R.id.maxTemp)

        fun bind(item: Hour) {

            Picasso.with(itemView.context).load("http:"+item.condition.icon).into(conditionIcon)
            time.text = item.time
            avgT.text = item.tempC.toString()


        }
    }
}