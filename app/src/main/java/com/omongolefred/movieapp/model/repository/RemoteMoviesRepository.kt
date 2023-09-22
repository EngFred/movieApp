package com.omongolefred.movieapp.model.repository

import com.omongolefred.movieapp.domain.dataSources.MovieRemoteDataSource
import com.omongolefred.movieapp.model.data.artistData.Actor
import javax.inject.Inject


class RemoteMoviesRepository @Inject constructor(
    private val movieRemoteDataSource : MovieRemoteDataSource)
{
    suspend fun getPopularMovies()  = movieRemoteDataSource.getPopularMovies()
    suspend fun getTopRatedMovies()  = movieRemoteDataSource.getTopRatedMovies()
    suspend fun getTopRatedShows()  = movieRemoteDataSource.getTopRatedShows()
    suspend fun getPopularShows()  = movieRemoteDataSource.getPopularShows()
    suspend fun getActors() : List<Actor> = movieRemoteDataSource.getActors()
    suspend fun getUpComingMovies() = movieRemoteDataSource.getUpcomingMovies()
    suspend fun getMovieDetail( movieId: Int ) = movieRemoteDataSource.getMovieDetail( movieId )
    suspend fun getShowDetail( seriesId: Int ) = movieRemoteDataSource.getEpisodeDetail( seriesId )

}