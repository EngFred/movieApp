package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class StoreUpcomingMoviesUseCase @Inject constructor(private val localRepository : LocalMoviesRepository ) {
    suspend operator fun invoke( list : List<MovieUpcoming> ) {
        return localRepository.storeUpcomingMovie( list )
    }

}