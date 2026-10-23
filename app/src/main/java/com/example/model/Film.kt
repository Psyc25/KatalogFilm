package com.example.model

import java.io.Serializable

data class Film(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val rating: Float,
    val duration: String,
    val director: String,
    val synopsis: String,
    val posterUrl: String,
    val youtubeTrailerUrl: String,
    val videoTrailerUrl: String,
    var isFavorite: Boolean = false,
    var userReview: String = ""
) : Serializable
