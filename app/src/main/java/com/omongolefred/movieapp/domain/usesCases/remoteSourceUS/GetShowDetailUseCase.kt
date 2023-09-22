package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.ShowDetailResponse
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetShowDetailUseCase @Inject constructor(
    private val remoteRepository : RemoteMoviesRepository
) {

    suspend operator fun invoke( seriesId: Int ) : ShowDetailResponse {
        return remoteRepository.getShowDetail( seriesId )
    }

}