package com.udacity.asteroidradar

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.database.AsteroidDataBase
import com.udacity.asteroidradar.database.DataBasePicture
import com.udacity.asteroidradar.model.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.*

class AsteroidRepository(private val database: AsteroidDataBase) {

    suspend fun refreshAsteroids(
        startDate: String = getToday(),
        endDate: String = getSeventhDay()
    ) {
        var asteroidList: ArrayList<Asteroid>
        withContext(Dispatchers.IO) {
            val asteroidResponseBody: ResponseBody = AsteroidApi.retrofitService.getAsteroids(
                startDate, endDate,
                Constants.API_KEY
            )
            asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody.string()))
            database.asteroidDataBaseDao.insertAll(*asteroidList.asDomainModel())
        }
    }
    suspend fun refreshPicture() {
        var picture: PictureOfDay
        withContext(Dispatchers.IO) {
            val pictureResponseBody: PictureOfDay = AsteroidApi.retrofitPicture.getPictureOfTheDay(
                Constants.API_KEY
            )
            picture = pictureResponseBody
            val g = DataBasePicture(picture.mediaType,picture.title,picture.url)
            database.pictureDataBaseDao.insertAll(g)
        }
    }
    suspend fun getAllAsteroids():List<Asteroid>{
        lateinit var result:List<Asteroid>
        withContext(Dispatchers.IO){
            result = database.asteroidDataBaseDao.getAllAsteroids()
        }
        return result
    }
    suspend fun getTodayAsteroids():List<Asteroid>{
        lateinit var result:List<Asteroid>
        withContext(Dispatchers.IO){
            result = database.asteroidDataBaseDao.getTodayAsteroids(getToday())
        }
        return result
    }
    suspend fun getPictureOfTheDay():PictureOfDay{
        lateinit var result:PictureOfDay
        withContext(Dispatchers.IO){
            result = database.pictureDataBaseDao.getTodayPicture()
        }
        return result
    }

}
