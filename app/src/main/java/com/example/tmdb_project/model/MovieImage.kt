package com.example.tmdb_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieImagesResponse(
    val backdrops: List<Image>,
    val posters: List<Image>
)

@Serializable
data class Image(
    @SerialName(value = "file_path")
    val filePath: String
)