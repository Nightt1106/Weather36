package com.night.weather36.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.night.weather36.WeatherRepository
import com.night.weather36.db.WeatherRoomDatabase
import com.night.weather36.model.Weather

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val repository : WeatherRepository
    private val TAG = WeatherViewModel::class.java.simpleName

    val allWeather :LiveData<List<Weather>>

    init {
        val weatherDao = WeatherRoomDatabase.getDatabase(application,viewModelScope).weatherDao()
        repository = WeatherRepository(weatherDao)
        allWeather = repository.allWeathers
    }

    /*fun insertAll(list: List<Weather>) = viewModelScope.launch {
        repository.insertAll(list)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }*/

    fun downloadData()  = viewModelScope.launch(Dispatchers.Default) {
        val tempList = mutableListOf<Weather>()
        val data = repository.getData()
        var n = 1
        data.records.location[0].weatherElement.forEach {
            it.time.forEach {itt ->
                tempList.add(
                    Weather(
                        id = n,
                        startTime = itt.startTime,
                        endTime = itt.endTime,
                        elementName = it.elementName,
                        parameterName = itt.parameter.parameterName,
                        parameterUnit = itt.parameter.parameterUnit
                    )
                )
                n+=1
                tempList.add(
                    Weather(
                        id = n,
                        startTime = itt.startTime,
                        endTime = itt.endTime,
                        elementName = it.elementName,
                        parameterName = itt.parameter.parameterName,
                        parameterUnit = itt.parameter.parameterUnit
                    )
                )
                n+=1
            }
        }
        //Log.d(TAG,tempList.toString())
        n = 1
        repository.deleteAll()
        repository.insertAll(tempList)
    }
}