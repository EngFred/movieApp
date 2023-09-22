package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class StorePopularShowsUseCase @Inject constructor( private val localRepository: LocalMoviesRepository ) {
    suspend operator fun invoke( list: List<ShowPopular> ){
        return localRepository.storePopularShows( list )
    }
}