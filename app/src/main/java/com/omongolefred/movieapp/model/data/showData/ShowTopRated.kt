package com.omongolefred.movieapp.model.data.showData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("top_rated_shows_table")
data class ShowTopRated(
    @PrimaryKey val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double,
    val vote_count: Int
)