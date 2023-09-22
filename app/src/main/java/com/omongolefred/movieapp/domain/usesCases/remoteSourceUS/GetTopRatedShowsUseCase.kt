package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetTopRatedShowsUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository  ) {

    suspend operator fun invoke() : List<ShowTopRated>{
        return remoteRepository.getTopRatedShows()
    }

}