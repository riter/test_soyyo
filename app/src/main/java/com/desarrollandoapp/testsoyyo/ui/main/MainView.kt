package com.desarrollandoapp.testsoyyo.ui.main

import com.desarrollandoapp.testsoyyo.data.entity.AstronomyPicture

interface MainView {
    fun showProgress(active: Boolean)
    fun showItems(items: List<AstronomyPicture>)
    fun launchPictureUi(picture: AstronomyPicture)
    fun showErrorList()
}