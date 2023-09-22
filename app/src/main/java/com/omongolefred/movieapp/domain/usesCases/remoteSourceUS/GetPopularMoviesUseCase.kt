package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository  ) {

    suspend operator fun invoke() : List<MoviePopular>{
        return remoteRepository.getPopularMovies()
    }

}