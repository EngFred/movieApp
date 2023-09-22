package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming

data class UpcomingMoviesResponse(
    val page: Int,
    val results: List<MovieUpcoming>,
    val total_pages: Int,
    val total_results: Int
)