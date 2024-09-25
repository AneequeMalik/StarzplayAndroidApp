package com.aneeque.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aneeque.domain.utils.room.Converters

@Database(entities = [MediaDetail::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}

