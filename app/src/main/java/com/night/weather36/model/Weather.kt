package com.night.weather36.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather36")
data class Weather(
    @PrimaryKey(autoGenerate = true) var id : Int ,
    var elementName :String,
    var startTime :String,
    var endTime :String,
    var parameterName :String,
    var parameterUnit :String? = null
)