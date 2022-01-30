package com.desarrollandoapp.testsoyyo.data.remote

import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture
import retrofit2.Response
import retrofit2.http.*

interface AstronomyPicturesApiService {

    @GET("planetary/apod")
    suspend fun getAllPictures(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("api_key") api_key: String = "zqjlllW9zHvlIzrPc8tflzuXbNWqIf1eNgyApxCD"
    ): Response<List<AstronomyPicture>>

}