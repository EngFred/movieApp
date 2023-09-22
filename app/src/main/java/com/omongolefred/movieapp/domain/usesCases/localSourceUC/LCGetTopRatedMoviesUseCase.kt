package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetTopRatedMoviesUseCase @Inject constructor(private val localRepository: LocalMoviesRepository ) {

    suspend operator fun invoke() : List<MovieTopRated> {
        return localRepository.getTopRatedMovies()
    }

}