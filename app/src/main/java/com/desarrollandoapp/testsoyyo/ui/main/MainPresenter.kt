package com.desarrollandoapp.testsoyyo.ui.main

import com.desarrollandoapp.testsoyyo.data.AstronomyPicturesRepository
import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture
import com.desarrollandoapp.testsoyyo.data.remote.ResultDataRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(var view: MainView?, var repository: AstronomyPicturesRepository): CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun loadPictures(startDate: String, endDate: String) {

        launch {

            withContext(Dispatchers.Main) {
                view?.showProgress(true)

                val result = repository.getPictures(startDate, endDate)

                when(result.status) {
                    ResultDataRepository.Status.SUCCESS -> {
                        var listPictures = result.data ?: emptyList()
                        listPictures = listPictures.filter { it.media_type == "image" }

                        view?.showProgress(false)
                        view?.showItems(listPictures)
                    }

                    ResultDataRepository.Status.ERROR -> {
                        view?.showProgress(false)
                        view?.showErrorList()
                    }
                }
            }
        }
    }

    fun openPictureDetail(picture: AstronomyPicture) {
        view?.launchPictureUi(picture)
    }

    fun onDestroy() {
        view = null
    }
}