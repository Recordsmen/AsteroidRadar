package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay

@Dao
interface AsteroidDataBaseDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("SELECT * FROM DatabaseAsteroid ORDER BY closeApproachDate")
    fun getAllAsteroids():List<Asteroid>

    @Query("SELECT * FROM DatabaseAsteroid WHERE closeApproachDate == :today")
    fun getTodayAsteroids(today: String):List<Asteroid>
}

@Dao
interface PictureDataBaseDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(picture: DataBasePicture)

    @Query("SELECT * FROM DataBasePicture")
    fun getTodayPicture(): PictureOfDay
}