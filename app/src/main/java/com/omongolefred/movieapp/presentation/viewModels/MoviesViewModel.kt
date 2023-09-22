package com.omongolefred.movieapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetPopularMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetTopRatedMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.LCGetUpComingMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StorePopularMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StoreTopRatedMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.localSourceUC.StoreUpcomingMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetPopularMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetTopRatedMoviesUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetUpComingMoviesUseCase
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.model.data.movieData.MovieTopRated
import com.omongolefred.movieapp.model.data.movieData.MovieUpcoming
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
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase : GetPopularMoviesUseCase ,
    private val getTopRatedMoviesUseCase : GetTopRatedMoviesUseCase ,
    private val upComingMoviesUseCase : GetUpComingMoviesUseCase ,

    private val storePopularMoviesUseCase : StorePopularMoviesUseCase ,
    private val storeTopRatedMoviesUseCase : StoreTopRatedMoviesUseCase ,
    private val storeUpcomingMoviesUseCase : StoreUpcomingMoviesUseCase ,

    private val lcPopularMoviesUseCase : LCGetPopularMoviesUseCase ,
    private val lcTopRatedMoviesUseCase : LCGetTopRatedMoviesUseCase,
    private val lcUpcomingMoviesUseCase : LCGetUpComingMoviesUseCase ,

    ) : ViewModel() {

    private val _popularMovies: MutableStateFlow<List<MoviePopular>> = MutableStateFlow( emptyList() )
    val popularMovies = _popularMovies.asStateFlow()

    private val _topRatedMovies: MutableStateFlow<List<MovieTopRated>> = MutableStateFlow( emptyList() )
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _upComingMovies: MutableStateFlow<List<MovieUpcoming>> = MutableStateFlow( emptyList() )
    val upComingMovies = _upComingMovies.asStateFlow()


    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {

        viewModelScope.launch( Dispatchers.IO + errorHandler ) {
            getUpComingMovies()
            getPopularMovies()
            getTopRatedMovies()
        }
    }

    private suspend fun getPopularMovies() {
        try {
            val result = getPopularMoviesUseCase.invoke()
            storePopularMoviesUseCase.invoke( result )
            _popularMovies.value = result
        } catch ( ex: Exception ) {
            when ( ex ) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    val popularMovies = lcPopularMoviesUseCase.invoke()
                    _popularMovies.value = popularMovies
                }
            }
        }
    }

    private suspend fun getTopRatedMovies() {
        try {
            val result = getTopRatedMoviesUseCase.invoke()
            storeTopRatedMoviesUseCase.invoke( result )
            _topRatedMovies.value = result
        } catch ( ex: Exception ) {
            when(ex) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                        val upcomingMovies = lcTopRatedMoviesUseCase.invoke()
                        _topRatedMovies.value = upcomingMovies
                    }
                else -> throw ex
            }
        }
    }

    private suspend fun getUpComingMovies() {
        try {
            val result = upComingMoviesUseCase.invoke()
            storeUpcomingMoviesUseCase.invoke( result )
            _upComingMovies.value = result
        } catch ( ex: Exception ) {
            when( ex ) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        val upComingMovies = lcUpcomingMoviesUseCase.invoke()
                        _upComingMovies.value = upComingMovies
                    }
                else -> throw ex
            }
        }
    }
}
