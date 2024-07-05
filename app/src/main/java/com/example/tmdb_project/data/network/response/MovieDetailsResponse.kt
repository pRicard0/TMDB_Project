package com.example.tmdb_project.data.network.response

import kotlinx.serialization.Serializable


@Serializable
class MovieDetailsResponse (
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Float,
    val runtime: Int,
    val tagline: String?,
)
