package com.aneeque.domain.utils.room

import androidx.room.TypeConverter
import com.aneeque.domain.utils.fromJson
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromIntegerList(list: ArrayList<Long>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toIntegerList(value: String): ArrayList<Long> {
        return Gson().fromJson<ArrayList<Long>>(value)
    }
}