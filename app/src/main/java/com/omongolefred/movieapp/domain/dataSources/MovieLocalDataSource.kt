package com.omongolefred.movieapp.domain.dataSources

import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import com.omongolefred.movieapp.model.database.MoviesDao
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor( private val dao : MoviesDao ) {

    suspend fun storePopularMovies( list : List<MoviePopular> )  = dao.storeAllPopularMovies( list )
    suspend fun storeTopRatedMovies(  list : List<MovieTopRated> ) = dao.storeAllTopRatedMovies( list )
    suspend fun storeTopRatedShows(  list : List<ShowTopRated>)  = dao.storeAllTopRatedShows( list )
    suspend fun storePopularShows(  list : List<ShowPopular>)  = dao.storeAllPopularShows( list )
    suspend fun storeActors(  list : List<Actor> ) = dao.storeAllActors( list )
    suspend fun storeUpcomingMovies( list : List<MovieUpcoming> )  = dao.storeUpComingMovies( list )

    suspend fun getPopularMovies()  = dao.retrieveAllPopularMovies()
    suspend fun getTopRatedMovies()  = dao.getAllTopRatedMovies()
    suspend fun getTopRatedShows()  = dao.retrieveAllTopRatedShows()
    suspend fun getPopularShows()  = dao.retrieveAllPopularShows()
    suspend fun getActors() : List<Actor> = dao.retrieveActors()
    suspend fun getUpComingMovies()  = dao.retrieveUpComingMovies()

}