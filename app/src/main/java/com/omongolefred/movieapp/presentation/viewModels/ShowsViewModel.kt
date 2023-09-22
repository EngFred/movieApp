package com.omongolefred.movieapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetPopularShowsUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetTopRatedShowsUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StorePopularShowsUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StoreTopRatedShowsUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetPopularShowsUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetTopRatedShowsUseCase
import com.omongolefred.movieapp.model.data.showData.ShowPopular
import com.omongolefred.movieapp.model.data.showData.ShowTopRated
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
class ShowsViewModel @Inject constructor(
    private val getPopularShowsUseCase : GetPopularShowsUseCase ,
    private val getTopRatedShowsUseCase : GetTopRatedShowsUseCase ,
    private val storePopularShowsUseCase : StorePopularShowsUseCase ,
    private val storeTopRatedShowsUseCase : StoreTopRatedShowsUseCase ,
    private val lcPopularShowsUseCase : LCGetPopularShowsUseCase ,
    private val lcTopRatedShowsUseCase : LCGetTopRatedShowsUseCase ,
) : ViewModel() {

    private val _topRatedShows: MutableStateFlow<List<ShowTopRated>> = MutableStateFlow( emptyList() )
    val topRatedShows = _topRatedShows.asStateFlow()

    private val _popularShows: MutableStateFlow<List<ShowPopular>> = MutableStateFlow( emptyList() )
    val popularShows = _popularShows.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        viewModelScope.launch( Dispatchers.IO +errorHandler ){
            getTopRatedShows()
            getPopularShows()
        }
    }

    private  suspend fun  getTopRatedShows() {
        try {
            val result = getTopRatedShowsUseCase.invoke()
            storeTopRatedShowsUseCase.invoke( result )
            _topRatedShows.value = result
        } catch ( ex : Exception ) {
            when( ex ) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    val topRatedShows = lcTopRatedShowsUseCase.invoke()
                    _topRatedShows.value = topRatedShows
                }
                else -> {
                    Log.e("I AM OMONGOLE", ex.message.toString())
                }
            }
        }
    }

    private  suspend fun  getPopularShows() {
        try {
            val result = getPopularShowsUseCase.invoke()
            storePopularShowsUseCase.invoke( result )
            _popularShows.value = result
        } catch ( ex : Exception ) {
            when(ex ) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    val popularShows = lcPopularShowsUseCase.invoke()
                    _popularShows.value = popularShows
                }
                else -> {
                    Log.e("I AM OMONGOLE II", ex.message.toString())
                }
            }
        }
    }
}