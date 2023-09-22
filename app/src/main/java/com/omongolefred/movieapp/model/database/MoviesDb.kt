package com.omongolefred.movieapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.data.artistData.KnownFor
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.data.showData.ShowTopRated

@Database( entities = [ ShowPopular::class, ShowTopRated::class, Actor::class, KnownFor::class, MovieUpcoming::class, MoviePopular::class, MovieTopRated::class ], version = 7, exportSchema = false )
@TypeConverters( MoviesTypeConverters::class )
abstract class MoviesDb : RoomDatabase() {
    abstract fun movieDao() : MoviesDao
}