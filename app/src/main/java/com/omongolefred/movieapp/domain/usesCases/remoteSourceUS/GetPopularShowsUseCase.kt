package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetPopularShowsUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository  ) {

    suspend operator fun invoke() : List<ShowPopular>{
        return remoteRepository.getPopularShows()
    }
}