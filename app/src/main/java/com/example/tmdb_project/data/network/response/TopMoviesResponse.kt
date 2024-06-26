package com.example.tmdb_project.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopMoviesResponse(
    val page: Int,
    val results: List<CardResponse>,
)