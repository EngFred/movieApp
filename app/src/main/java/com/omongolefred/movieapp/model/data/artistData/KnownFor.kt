package com.omongolefred.movieapp.model.data.artistData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("known_for")
data class KnownFor(
    val adult: Boolean ,
    val first_air_date: String ,
    @PrimaryKey val id: Int ,
    val name: String ,
    val original_language: String ,
    val original_name: String ,
    val original_title: String ,
    val overview: String ,
    val poster_path: String ,
    val release_date: String ,
    val title: String ,
    val video: Boolean ,
    val vote_average: Double ,
    val vote_count: Int
)