package com.omongolefred.movieapp.model.data.showData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("popular_shows_table")
data class ShowPopular(
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