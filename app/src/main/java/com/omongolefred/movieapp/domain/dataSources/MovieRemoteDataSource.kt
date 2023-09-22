package com.omongolefred.movieapp.domain.dataSources

import com.omongolefred.movieapp.model.api.MovieApi
import com.omongolefred.movieapp.model.data.MovieDetailResponse
import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.utils.Resource
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getPopularMovies()  = api.getPopularMovies().results
    suspend fun getTopRatedMovies()  = api.getTopRatedMovies().results
    suspend fun getTopRatedShows()  = api.getTopRatedShows().results
    suspend fun getPopularShows()  = api.getPopularShows().results
    suspend fun getActors() : List<Actor> = api.getActors().results
    suspend fun getUpcomingMovies()  = api.getUpComingMovies().results
    suspend fun getMovieDetail( movieId: Int )  = api.getMovieDetail(movieId)
    suspend fun getEpisodeDetail( seriesId: Int ) = api.getShowDetail( seriesId )

}