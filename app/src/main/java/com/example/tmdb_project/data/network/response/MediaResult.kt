package com.example.tmdb_project.data.network.response

import okhttp3.MediaType

interface MediaResult {

    interface MediaResult {
        val backdropPath: String
        val genreIds: List<Int>
        val id: Int
        val originalLanguage: String
        val overview: String
        val popularity: Double
        val posterPath: String
        val voteAverage: Double
        val voteCount: Int
        var mediaType: MediaType
        val tagline:String
    }
}