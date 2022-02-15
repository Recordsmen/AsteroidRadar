package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDataBaseDao{
    @Query("SELECT * FROM asteroid_detail WHERE id = :key")
    suspend fun get(key: Long): Asteroid
}