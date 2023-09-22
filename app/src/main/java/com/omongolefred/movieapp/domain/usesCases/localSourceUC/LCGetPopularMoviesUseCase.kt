package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetPopularMoviesUseCase @Inject constructor(private val localRepository: LocalMoviesRepository ) {

    suspend operator fun invoke() : List<MoviePopular>{
        return localRepository.getPopularMovies()
    }

}