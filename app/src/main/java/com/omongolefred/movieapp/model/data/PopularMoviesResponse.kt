package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.movieData.MoviePopular

data class PopularMoviesResponse(
    val page: Int ,
    val results: List<MoviePopular> ,
    val total_pages: Int ,
    val total_results: Int
)