package com.omongolefred.movieapp.presentation.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.databinding.ActivityMovieDetailBinding
import com.omongolefred.movieapp.model.data.MovieDetailResponse
import com.omongolefred.movieapp.utils.Constants
import com.omongolefred.movieapp.presentation.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private var _binding: ActivityMovieDetailBinding? = null
    private val binding
        get()= checkNotNull( _binding )

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate( layoutInflater )
        setTheme( R.style.FullImageActivity )
        setContentView(  binding.root )

        val movieId = intent.getIntExtra("movie_id", 0 )

        detailViewModel.getMovieDetail( movieId )

        lifecycleScope.launch {
            repeatOnLifecycle(  Lifecycle.State.STARTED ) {
                detailViewModel.movieDetail.collect{
                    if ( it == null ) {
                        showProgressBar()
                    } else {
                        hideProgressBar()
                        upDateUi( it )
                    }
                }
            }
        }

    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.detailLayout.visibility = View.VISIBLE
        binding.root.setBackgroundColor( Color.BLACK )
        binding.posterImage.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.detailLayout.visibility = View.INVISIBLE
        binding.root.setBackgroundColor( Color.WHITE )
        binding.posterImage.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun upDateUi( movie : MovieDetailResponse) {
        Glide.with( this ).load( Uri.parse("${Constants.BASE_IMAGE_URL}${movie.poster_path}") ).placeholder( R.drawable.m_placeholder ).into( binding.posterImage )
        binding.tvTitle.text = movie.original_title
        binding.tvTagline.text = movie.tagline
        binding.ratingBar.rating = movie.vote_average.toFloat() / 2
        binding.tvReleaseDate.text = movie.release_date
        binding.tvRating.text = movie.vote_average.toString()
        binding.tvRuntime.text = "${movie.runtime} minutes"
        binding.tvBudget.text = "$${movie.budget}"
        binding.tvRevenue.text = "$${movie.revenue}"
        binding.tvGenre.text = movie.genres[0].name
        binding.tvProductionCompany.text = movie.production_companies[0].name
        binding.tvProductionCountry.text = movie.production_countries[0].name
        binding.tvOverview.text = movie.overview

        binding.posterImage.setOnClickListener {
            val intent = Intent( this, ArtistImageActivity::class.java )
            intent.putExtra("image_path", "${Constants.BASE_IMAGE_URL}${movie.poster_path}" )
            startActivity( intent )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}