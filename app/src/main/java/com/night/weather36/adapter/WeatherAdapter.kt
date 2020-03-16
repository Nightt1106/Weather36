package com.night.weather36.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.night.weather36.R
import com.night.weather36.SecondActivity

import com.night.weather36.model.Weather
import kotlinx.android.synthetic.main.recycler_item.view.*

class WeatherAdapter(private val list : List<Weather>, private val app:AppCompatActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
                WeatherViewHolder(view)
            }
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_image,parent,false)
                ImageViewHolder(view)
            }
            else -> {
                null!!
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position%2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 ->{
                val tempList = list[position]
                holder as WeatherViewHolder
                holder.bindTo(tempList)
                holder.itemView.setOnClickListener {
                    app.startActivity(
                        Intent(app.baseContext,SecondActivity::class.java)
                            .putExtra("startTime",tempList.startTime)
                            .putExtra("endTime",tempList.endTime)
                            .putExtra("parameterName",tempList.parameterName+tempList.parameterUnit)
                    )
                }
            }
            1 ->{
                holder as ImageViewHolder
            }
        }
    }

    inner class  ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){

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