package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.ResponseBody


object AsteroidApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    interface AsteroidApiService {
        @GET(Constants.HTTP_GET_NEO_FEED_PATH)
        suspend fun getAsteroids(
            @Query(Constants.QUERY_START_DATE_PARAM) startDate: String,
            @Query(Constants.QUERY_END_DATE_PARAM) endDate: String,
            @Query(Constants.QUERY_API_KEY_PARAM) apiKey: String): ResponseBody
    }

    interface PictureOfTheDay {
        @GET(Constants.HTTP_GET_APOD_PATH)
        suspend fun getPictureOfTheDay(
            @Query(Constants.QUERY_API_KEY_PARAM) apiKey: String): PictureOfDay
    }

    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
    val retrofitPicture: PictureOfTheDay by lazy{
        retrofit.create(PictureOfTheDay::class.java)
    }

}
