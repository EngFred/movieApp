package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class StoreTopRatedShowsUseCase @Inject constructor(private val localRepository : LocalMoviesRepository ) {

    suspend operator fun invoke( list : List<ShowTopRated> ) {
        return localRepository.storeTopRatedShows( list )
    }

}