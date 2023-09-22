package com.omongolefred.movieapp.model.api

import com.omongolefred.movieapp.model.data.ActorsResponse
import com.omongolefred.movieapp.model.data.PopularMoviesResponse
import com.omongolefred.movieapp.model.data.PopularShowsResponse
import com.omongolefred.movieapp.model.data.TopRatedMoviesResponse
import com.omongolefred.movieapp.model.data.TopRatedShowsResponse
import com.omongolefred.movieapp.model.data.UpcomingMoviesResponse
import com.omongolefred.movieapp.model.data.ShowDetailResponse
import com.omongolefred.movieapp.model.data.MovieDetailResponse
import com.omongolefred.movieapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies() : PopularMoviesResponse

    @GET("movie/top_rated?api_key=${API_KEY}")
    suspend fun getTopRatedMovies() : TopRatedMoviesResponse

    @GET("tv/top_rated?api_key=${API_KEY}")
    suspend fun getTopRatedShows() : TopRatedShowsResponse

    @GET("tv/popular?api_key=${API_KEY}")
    suspend fun getPopularShows() : PopularShowsResponse

    @GET("person/popular?api_key=${API_KEY}")
    suspend fun getActors() : ActorsResponse

    @GET("movie/upcoming?api_key=${API_KEY}")
    suspend fun getUpComingMovies() : UpcomingMoviesResponse

    @GET("movie/{movie_id}?api_key=${API_KEY}")
    suspend fun getMovieDetail(@Path("movie_id") movieId : Int ) : MovieDetailResponse

    @GET("tv/{series_id}?api_key=${API_KEY}")
    suspend fun getShowDetail(@Path("series_id") seriesId : Int ) : ShowDetailResponse

}