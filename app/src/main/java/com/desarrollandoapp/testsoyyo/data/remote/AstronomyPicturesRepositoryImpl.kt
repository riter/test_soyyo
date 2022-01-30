package com.desarrollandoapp.testsoyyo.data.remote

import com.desarrollandoapp.testsoyyo.data.AstronomyPicturesRepository
import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture

class AstronomyPicturesRepositoryImpl (
    private val apiService: AstronomyPicturesApiService
) : BaseDataRepository(), AstronomyPicturesRepository {

    override suspend fun getPictures(startDate: String, endDate: String): ResultDataRepository<List<AstronomyPicture>> {
        return getResult { apiService.getAllPictures(startDate, endDate) }
    }
}