package com.carplayground.data.local

import com.carplayground.room.Car

interface CarsDataSource {
    suspend fun getCars() : List<Car>
    suspend fun insertCars(cars : List<Car>)
}