package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetPopularShowsUseCase @Inject constructor(private val localRepository: LocalMoviesRepository ) {
    suspend operator fun invoke() : List<ShowPopular>{
        return localRepository.getPopularShows()
    }
}