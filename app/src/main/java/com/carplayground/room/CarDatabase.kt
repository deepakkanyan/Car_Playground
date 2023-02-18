package com.carplayground.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Car::class], version = 1)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}