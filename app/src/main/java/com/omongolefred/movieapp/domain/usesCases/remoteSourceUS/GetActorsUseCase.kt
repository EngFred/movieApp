package com.omongolefred.movieapp.domain.usesCases.remoteSourceUS

import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import javax.inject.Inject

class GetActorsUseCase @Inject constructor( private val remoteRepository : RemoteMoviesRepository ) {

    suspend operator fun invoke() : List<Actor>{
        return remoteRepository.getActors()
    }

}