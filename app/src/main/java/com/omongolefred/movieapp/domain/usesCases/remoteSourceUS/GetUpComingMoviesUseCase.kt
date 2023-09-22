package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetUpComingMoviesUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository  ) {
    suspend operator fun invoke() : List<MovieUpcoming> {
        return remoteRepository.getUpComingMovies()
    }
}