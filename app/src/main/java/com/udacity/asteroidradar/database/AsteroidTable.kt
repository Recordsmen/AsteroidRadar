package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_detail")
data class Asteroid (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "codename")
    val codename: String,
    @ColumnInfo(name = "closeApproachDate")
    val closeApproachDate: String,
    @ColumnInfo(name = "absoluteMagnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimatedDiameter")
    val estimatedDiameter: Double,
    @ColumnInfo(name = "relativeVelocity")
    val relativeVelocity: Double,
    @ColumnInfo(name = "distanceFromEarth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "isPotentiallyHazardous")
    val isPotentiallyHazardous: Boolean
    )