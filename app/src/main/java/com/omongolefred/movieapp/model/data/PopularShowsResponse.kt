package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.showData.ShowPopular

data class PopularShowsResponse(
    val page: Int,
    val results: List<ShowPopular>,
    val total_pages: Int,
    val total_results: Int
)