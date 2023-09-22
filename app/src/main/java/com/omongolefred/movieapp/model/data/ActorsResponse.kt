package com.omongolefred.movieapp.model.data

import com.omongolefred.movieapp.model.data.artistData.Actor

data class ActorsResponse(
    val page: Int ,
    val results: List<Actor> ,
    val total_pages: Int ,
    val total_results: Int
)