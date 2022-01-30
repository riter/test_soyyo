package com.desarrollandoapp.testsoyyo.data.entity

import com.squareup.moshi.Json

data class AstronomyPicture(
    @Json(name = "copyright")
    val copyright: String,

    @Json(name = "date")
    val date: String,

    @Json(name = "explanation")
    val explanation: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "media_type")
    val media_type: String,
)
