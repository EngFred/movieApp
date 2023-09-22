package com.omongolefred.movieapp.model.data.movieData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "upcoming_movie_table" )
data class MovieUpcoming(
    val adult: Boolean,
    @PrimaryKey val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)