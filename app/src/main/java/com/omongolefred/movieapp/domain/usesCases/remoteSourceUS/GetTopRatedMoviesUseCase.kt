package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository ) {

    suspend operator fun invoke() : List<MovieTopRated> {
        return remoteRepository.getTopRatedMovies()
    }

}