package com.omongolefred.movieapp.utils

sealed class Resource<out R>{
    data class Success<out R>( val result: R ) : Resource<R>() //we have a result
    data class Failure( val message : String) : Resource<Nothing>() //no result
    data object Loading: Resource<Nothing>() //we are not getting any result
}