package com.night.weather36


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.night.weather36.adapter.WeatherAdapter

import com.night.weather36.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var weatherViewModel : WeatherViewModel
    private final val PREFS_NAME  = "MyPreFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherViewModel.allWeather.observe(this, Observer {
            it?.let {
                textView_loading.visibility = View.GONE
                val adapter = WeatherAdapter(it,this)
                recycler.adapter = adapter
            }
        })
        val setting = getSharedPreferences(PREFS_NAME,0)
        if (setting.getBoolean("my_first_time",true)){
            Log.d(TAG,"First Time")
            weatherViewModel.downloadData()
            setting.edit().putBoolean("my_first_time",false).apply()
        } else {
            MaterialAlertDialogBuilder(this).setMessage("歡迎回來")
                .setPositiveButton("OK",null)
                .show()
        }

        findViewById<Button>(R.id.button_refresh).setOnClickListener {
            weatherViewModel.downloadData()
        }
    }
}
