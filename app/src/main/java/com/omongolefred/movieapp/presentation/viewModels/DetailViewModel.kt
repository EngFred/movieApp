package com.omongolefred.movieapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetMovieDetailUseCase
import com.omongolefred.movieapp.domain.usesCases.remoteSourceUS.GetShowDetailUseCase
import com.omongolefred.movieapp.model.data.ShowDetailResponse
import com.omongolefred.movieapp.model.data.MovieDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val getMovieDetailUseCase: GetMovieDetailUseCase ,
    private val getShowDetailUseCase : GetShowDetailUseCase
) : ViewModel() {

    private val _movieDetail: MutableStateFlow<MovieDetailResponse?> = MutableStateFlow(null)
    val movieDetail = _movieDetail.asStateFlow()

    private val _showDetail: MutableStateFlow<ShowDetailResponse?> = MutableStateFlow(null)
    val showDetail = _showDetail.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getMovieDetail( movieId : Int ) {
        viewModelScope.launch( Dispatchers.IO + errorHandler ) {
            val result = getMovieDetailUseCase.invoke( movieId )
            _movieDetail.value = result
        }
    }

    fun getShowDetail( seriesId : Int ) {
        viewModelScope.launch( Dispatchers.IO + errorHandler ) {
            val result = getShowDetailUseCase.invoke( seriesId )
            _showDetail.value = result
        }
    }

}