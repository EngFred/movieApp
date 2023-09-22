package com.omongolefred.movieapp.di

import android.content.Context
import androidx.room.Room
import com.omongolefred.movieapp.domain.dataSources.MovieRemoteDataSource
import com.omongolefred.movieapp.model.api.MovieApi
import com.omongolefred.movieapp.model.database.MoviesDb
import com.omongolefred.movieapp.model.repository.RemoteMoviesRepository
import com.omongolefred.movieapp.utils.Constants
import com.omongolefred.movieapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( SingletonComponent::class )
class AppModule {

    @Provides
    fun provideMovieRemoteDataSource( api : MovieApi ) = MovieRemoteDataSource( api )

    @Provides
    fun provideMoviesRepository( dataSource : MovieRemoteDataSource ) = RemoteMoviesRepository( dataSource )

    @Provides
    fun provideMovieDb(  @ApplicationContext context: Context ) = Room.databaseBuilder( context, MoviesDb::class.java, "movies_db" ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(  moviesDb : MoviesDb ) = moviesDb.movieDao()

    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory( GsonConverterFactory.create() )
        .build()

    @Provides
    fun provideMovieApi( retrofit: Retrofit ) : MovieApi = retrofit.create( MovieApi::class.java )
}