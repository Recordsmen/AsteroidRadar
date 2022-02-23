package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.database.AsteroidDataBase
import retrofit2.HttpException

class DeleteData(appContext: Context, params: WorkerParameters):CoroutineWorker(appContext,params) {
    companion object{
        const val WORK_NAME = "DeletePreviousDay"
    }
    override suspend fun doWork(): Result {
        val database = AsteroidDataBase.getDatabase(applicationContext)
        val repository = AsteroidRepository(database)
        return try{
            repository.deleteOldData()
            Result.success()
        } catch (exception: HttpException){
            Result.retry()
        }

    }

}