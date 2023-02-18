package com.carplayground.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {
    @Insert
    suspend fun insert(car: Car)
    @Insert
    suspend fun insertAll(cars: List<Car>)

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Car>

    @Query("SELECT * FROM cars WHERE id = :id")
    suspend fun getCarById(id: Int): Car?

    @Query("DELETE FROM cars")
    suspend fun deleteAll()
}