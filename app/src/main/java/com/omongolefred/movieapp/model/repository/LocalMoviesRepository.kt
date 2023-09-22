package com.omongolefred.movieapp.model.repository

import com.omongolefred.movieapp.domain.dataSources.MovieLocalDataSource
import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import javax.inject.Inject

class LocalMoviesRepository @Inject constructor ( private val movieLocalDataSource : MovieLocalDataSource ) {

    suspend fun storePopularMovies( list : List<MoviePopular> )  = movieLocalDataSource.storePopularMovies( list )
    suspend fun storeTopRatedMovies(  list : List<MovieTopRated> ) = movieLocalDataSource.storeTopRatedMovies( list )
    suspend fun storeTopRatedShows(  list : List<ShowTopRated>)  = movieLocalDataSource.storeTopRatedShows( list )
    suspend fun storePopularShows(  list : List<ShowPopular>)  = movieLocalDataSource.storePopularShows( list )
    suspend fun storeActors(  list : List<Actor> ) = movieLocalDataSource.storeActors( list )
    suspend fun storeUpcomingMovie( list : List<MovieUpcoming>)  = movieLocalDataSource.storeUpcomingMovies( list )

    suspend fun getPopularMovies() = movieLocalDataSource.getPopularMovies()
    suspend fun getTopRatedMovies()  = movieLocalDataSource.getTopRatedMovies()
    suspend fun getTopRatedShows()  = movieLocalDataSource.getTopRatedShows()
    suspend fun getPopularShows()  = movieLocalDataSource.getPopularShows()
    suspend fun getActors() : List<Actor> = movieLocalDataSource.getActors()
    suspend fun getUpcomingMovies()  = movieLocalDataSource.getUpComingMovies()

}