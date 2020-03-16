package com.night.weather36.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.night.weather36.model.Weather
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Weather::class],version = 1)
public abstract class WeatherRoomDatabase :RoomDatabase(){

    private val tag = WeatherRoomDatabase::class.java.simpleName

    abstract fun weatherDao() : WeatherDao

    private class WeatherDatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                Log.d(WeatherRoomDatabase::class.java.simpleName,"${WeatherRoomDatabase::class.java.simpleName} is called")
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE :WeatherRoomDatabase? = null
        fun getDatabase(context: Context,scope: CoroutineScope) :WeatherRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        WeatherRoomDatabase::class.java,
                        "Weather_Database"
                    ).addCallback(WeatherDatabaseCallback(scope)).build()
                INSTANCE = instance
                //Return
                instance
            }
        }
    }
}