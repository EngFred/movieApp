package com.omongolefred.movieapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetActorsUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StoreActorsUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetActorsUseCase
import com.omongolefred.movieapp.model.data.artistData.Actor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getActorsUseCase : GetActorsUseCase ,
    private val storeActorsUseCase : StoreActorsUseCase ,
    private val lcActorsUseCase : LCGetActorsUseCase ,
) :  ViewModel() {

    private val _actors: MutableStateFlow<List<Actor>> = MutableStateFlow( emptyList() )
    val actors = _actors.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {

        viewModelScope.launch( Dispatchers.IO + errorHandler ) {
            getActors()
        }

    }

    private suspend fun getActors() {
        try {
            val result=getActorsUseCase.invoke()
            storeActorsUseCase.invoke(result)
            _actors.value=result
        } catch (ex : Exception) {
            when (ex) {
                is UnknownHostException , is ConnectException , is HttpException -> {
                    val upComingMovies=lcActorsUseCase.invoke()
                    _actors.value=upComingMovies
                }
                else -> {
                    Log.i("OMOOOOOO", ex.toString())
                }
            }
        }
    }
}