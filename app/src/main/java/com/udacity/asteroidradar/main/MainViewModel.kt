package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidRepository
import com.udacity.asteroidradar.database.AsteroidDataBase
import com.udacity.asteroidradar.model.PictureOfDay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDataBase.getDatabase(application.applicationContext)

    private val asteroidRepository = AsteroidRepository(database)

    private val _navigateToAsteroid = MutableLiveData<Asteroid>()
    val navigateToAsteroid:LiveData<Asteroid> get() = _navigateToAsteroid

    private var _response = MutableLiveData<List<Asteroid>>()
    val response:LiveData<List<Asteroid>> get() = _response

    private var _image = MutableLiveData<PictureOfDay>()
    val image:LiveData<PictureOfDay> get() = _image

    init {
        refreshAsteroids()
        getPicture()
        getAsteroidsFromCache()
        getPictureFromCache()
    }

    fun onAsteroidClicked(asteroid: Asteroid){
        _navigateToAsteroid.value = asteroid
    }
    fun onDetailAsteroidNavigated() {
        _navigateToAsteroid.value = null
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                asteroidRepository.refreshAsteroids()
                _response.value = asteroidRepository.getAllAsteroids()
                Log.i("MainViewModel","Get_asteroids_success")

            } catch (e: Exception) {
                println("Exception refreshing data: $e.message")
            }
        }
    }
    private fun getAsteroidsFromCache(){
        viewModelScope.launch {
            _response.value = asteroidRepository.getAllAsteroids()
        }
    }
    private fun getPicture(){
        viewModelScope.launch {
            try{
                asteroidRepository.refreshPicture()
                _image.value = asteroidRepository.getPictureOfTheDay()
                Log.i("MainViewModel","Get_picture_success")
            } catch (e: Exception) {
                println("Exception refreshing data: $e.message")
            }

        }
    }
    private fun getPictureFromCache(){
        viewModelScope.launch {
            _image.value = asteroidRepository.getPictureOfTheDay()
        }
    }
    fun getTodayAsteroids(){
        viewModelScope.launch {
            try {
                _response.value = asteroidRepository.getTodayAsteroids()
            } catch (e: Exception){
                println("Exception refreshing data: $e.message")
            }
        }
    }
    fun getAllAsteroids(){
        viewModelScope.launch {
            try {
                _response.value = asteroidRepository.getAllAsteroids()
            } catch (e: Exception){
                println("Exception refreshing data: $e.message")
            }
        }
    }
    fun getAllSavedAsteroids(){
        viewModelScope.launch {
            try {
                _response.value = asteroidRepository.getAllAsteroids()
            } catch (e: Exception){
                println("Exception refreshing data: $e.message")
            }
        }
    }


}