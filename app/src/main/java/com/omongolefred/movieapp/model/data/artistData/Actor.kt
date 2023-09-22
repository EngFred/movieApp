package com.omongolefred.movieapp.model.data.artistData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("actors_table")
data class Actor(
    val adult: Boolean ,
    val gender: Int ,
    @PrimaryKey val id: Int ,
    val known_for: List<KnownFor> ,
    val known_for_department: String? ,
    val name: String? ,
    val popularity: Double ,
    val profile_path: String?
)