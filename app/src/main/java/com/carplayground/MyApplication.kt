package com.carplayground

import android.app.Application
import androidx.room.Room
import com.carplayground.room.CarDatabase

class MyApplication : Application() {


    val appDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CarDatabase::class.java, "car-database"
        ).build()
    }
}