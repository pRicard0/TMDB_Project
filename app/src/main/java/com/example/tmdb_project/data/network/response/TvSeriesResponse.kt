package com.example.tmdb_project.data.network.response

import com.google.gson.annotations.SerializedName

class TvSeriesResponse {
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val first_air_date: String,

    val page: Int,
    val result: List,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

}