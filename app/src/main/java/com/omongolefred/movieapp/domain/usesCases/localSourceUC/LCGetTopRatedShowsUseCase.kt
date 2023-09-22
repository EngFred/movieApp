package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetTopRatedShowsUseCase @Inject constructor(private val localRepository: LocalMoviesRepository) {

    suspend operator fun invoke() : List<ShowTopRated>{
        return localRepository.getTopRatedShows()
    }

}