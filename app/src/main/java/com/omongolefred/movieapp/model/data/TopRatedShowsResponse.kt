package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.showData.ShowTopRated

data class TopRatedShowsResponse(
    val page: Int ,
    val results: List<ShowTopRated> ,
    val total_pages: Int ,
    val total_results: Int
)