package com.omongolefred.movieapp.domain.usesCases.localSourceUC

import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.model.repository.LocalMoviesRepository
import javax.inject.Inject

class LCGetActorsUseCase @Inject constructor(private val localRepository : LocalMoviesRepository) {

    suspend operator fun invoke() : List<Actor>{
        return localRepository.getActors()
    }

}