package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetUpComingMoviesUseCase @Inject constructor(private val localRepository: LocalMoviesRepository) {
    suspend operator fun invoke() : List<MovieUpcoming> {
        return localRepository.getUpcomingMovies()
    }
}