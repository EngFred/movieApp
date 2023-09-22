package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.movieData.MovieTopRated

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<MovieTopRated>,
    val total_pages: Int,
    val total_results: Int
)