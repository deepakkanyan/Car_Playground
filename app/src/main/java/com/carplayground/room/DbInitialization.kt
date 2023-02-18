package com.carplayground.room

import android.content.Context
import androidx.room.Room

object DbInitialization {


    fun initDb(applicationContext : Context) {
        val db = Room.databaseBuilder(
            applicationContext,
            CarDatabase::class.java, "car-database"
        ).build()


    }




}