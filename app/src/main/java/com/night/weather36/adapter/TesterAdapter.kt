package com.night.weather36.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.night.weather36.R
import com.night.weather36.model.Weather
import kotlinx.android.synthetic.main.recycler_item.view.*

class TesterAdapter(private val list : List<Weather>) : RecyclerView.Adapter<TesterAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return  WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    inner class  WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val startTime = view.textView_start_time
        val endTime = view.textView_end_time
        val temOfC = view.textView_temp
        fun bindTo(weather: Weather){
            startTime.text = weather.startTime
            endTime.text = weather.endTime
            temOfC.text = weather.parameterName + weather.parameterUnit
        }
    }
}