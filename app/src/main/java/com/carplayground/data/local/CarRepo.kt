package com.carplayground.data.local

import com.carplayground.room.Car
import com.carplayground.room.CarDatabase
import javax.inject.Inject

class CarRepo @Inject constructor(private val db : CarDatabase) : CarsDataSource {
    override suspend fun getCars(): List<Car> {
        return db.carDao().getAllCars()
    }

    override suspend fun insertCars(cars: List<Car>) {
         db.carDao().insertAll(cars)
    }


}