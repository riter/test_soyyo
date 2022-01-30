package com.desarrollandoapp.testsoyyo.data

import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture
import com.desarrollandoapp.testsoyyo.data.remote.ResultDataRepository

interface AstronomyPicturesRepository {
    suspend fun getPictures(startDate: String, endDate: String): ResultDataRepository<List<AstronomyPicture>>
}