package com.example.tmdb_project.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    val id: Int,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val vote_average: Float,
    val release_date: String,
    val title: String
)