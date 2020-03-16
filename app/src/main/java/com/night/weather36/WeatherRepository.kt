package com.night.weather36

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.night.weather36.db.WeatherDao
import com.night.weather36.model.Data
import com.night.weather36.model.Weather
import java.net.URL

class WeatherRepository(private val weatherDao: WeatherDao){
    private val TAG = WeatherRepository::class.java.simpleName
    val allWeathers : LiveData<List<Weather>> = weatherDao.getAll()
    suspend fun insertAll(list: List<Weather>) {
        weatherDao.insertAll(list)
        Log.d(TAG,"123")
    }
    suspend fun deleteAll() = weatherDao.deleteAll()
    //`````````````````````````````````````````````````````````
    val dataUrl: String = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-FD8A287C-CEED-428E-B927-883CE1FE3449&locationName=%E8%87%BA%E5%8C%97%E5%B8%82"
    suspend fun getData() :Data{
        val jsonText = URL(dataUrl).readText()
        val gson = Gson()
        val json = gson.fromJson(jsonText,Data::class.java)
        return json
    }
}