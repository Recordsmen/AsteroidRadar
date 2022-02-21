package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    DatabaseAsteroid::class,
    DataBasePicture::class
                     ], version = 2)

abstract class AsteroidDataBase:RoomDatabase() {

    abstract val asteroidDataBaseDao: AsteroidDataBaseDao
    abstract val pictureDataBaseDao: PictureDataBaseDao

    companion object{
        @Volatile
        private var INSTANCE:AsteroidDataBase? = null

        fun getDatabase(context: Context): AsteroidDataBase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDataBase::class.java,
                        "asteroid_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
                }
            }
        }

    }

