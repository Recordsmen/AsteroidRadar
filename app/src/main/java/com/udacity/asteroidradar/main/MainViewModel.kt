package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {
    private val _navigateToAsteroid = MutableLiveData<Asteroid>()
    val navigateToAsteroid:LiveData<Asteroid> get() = _navigateToAsteroid


    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToAsteroid.value = asteroid
    }
    fun onDetailAsteroidNavigated() {
        _navigateToAsteroid.value = null
    }
}