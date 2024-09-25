package com.aneeque.starzplayandroidsimulatedvideoapp.base

import android.app.Application
import androidx.room.Room
import com.aneeque.domain.room.Database

class StarzplayApp: Application() {
    companion object {
        var database: Database? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "starzplay-db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}