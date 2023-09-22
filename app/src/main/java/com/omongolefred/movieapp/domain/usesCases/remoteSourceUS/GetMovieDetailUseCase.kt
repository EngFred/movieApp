package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.MovieDetailResponse
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository ) {

    suspend operator fun invoke( movieId: Int ) : MovieDetailResponse {
        return remoteRepository.getMovieDetail( movieId )
    }

}