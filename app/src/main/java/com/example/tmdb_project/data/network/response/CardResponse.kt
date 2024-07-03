package com.example.tmdb_project.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    val id: Int,
    val poster_path: String,
    val title: String
)