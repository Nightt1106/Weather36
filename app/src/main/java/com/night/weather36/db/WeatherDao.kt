package com.night.weather36.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.night.weather36.model.Weather

@Dao
interface WeatherDao{
    @Query("SELECT * FROM weather36 WHERE elementName = 'MinT'")
    fun getAll() : LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Weather>)

    @Query("DELETE FROM weather36")
    suspend fun deleteAll()
}