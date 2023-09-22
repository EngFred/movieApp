package com.omongolefred.movieapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.data.showData.ShowTopRated

@Dao
interface MoviesDao {

    @Insert( onConflict = REPLACE )
    suspend fun storeAllPopularMovies( list : List<MoviePopular>)

    @Query("SELECT * FROM popular_movies_table")
    suspend fun retrieveAllPopularMovies( ) : List<MoviePopular>

    @Insert( onConflict = REPLACE )
    suspend fun storeAllTopRatedMovies( list : List<MovieTopRated> )

    @Query("SELECT * FROM top_rated_movies_table")
    suspend fun getAllTopRatedMovies( ) : List<MovieTopRated>

    @Insert( onConflict = REPLACE )
    suspend fun storeUpComingMovies( list : List<MovieUpcoming> )

    @Query("SELECT * FROM upcoming_movie_table")
    suspend fun retrieveUpComingMovies( ) : List<MovieUpcoming>

    @Insert( onConflict = REPLACE )
    suspend fun storeAllPopularShows( list : List<ShowPopular>)

    @Query("SELECT * FROM popular_shows_table")
    suspend fun retrieveAllPopularShows() : List<ShowPopular>

    @Insert( onConflict = REPLACE )
    suspend fun storeAllTopRatedShows( list : List<ShowTopRated>)

    @Query("SELECT * FROM top_rated_shows_table")
    suspend fun retrieveAllTopRatedShows() : List<ShowTopRated>

    @Insert( onConflict = REPLACE )
    suspend fun storeAllActors( list : List<Actor>)

    @Query("SELECT * FROM actors_table")
    suspend fun retrieveActors() : List<Actor>

}