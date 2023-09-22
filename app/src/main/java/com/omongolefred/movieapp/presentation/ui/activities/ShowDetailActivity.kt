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
import com.omongolefred.movieapp.databinding.ActivityShowDetailBinding
import com.omongolefred.movieapp.model.data.ShowDetailResponse
import com.omongolefred.movieapp.utils.Constants
import com.omongolefred.movieapp.presentation.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowDetailActivity : AppCompatActivity() {
    private var _binding: ActivityShowDetailBinding? = null
    private val binding
        get()= checkNotNull( _binding )

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShowDetailBinding.inflate( layoutInflater )
        setTheme( R.style.FullImageActivity )
        setContentView(  binding.root )

        val seriesId = intent.getIntExtra("series_id", 0)
        detailViewModel.getShowDetail( seriesId )

        lifecycleScope.launch {
            repeatOnLifecycle(  Lifecycle.State.STARTED ) {
                detailViewModel.showDetail.collect{
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
    private fun upDateUi( show: ShowDetailResponse) {
        Glide.with( this ).load( Uri.parse("${Constants.BASE_IMAGE_URL}${show.poster_path}") ).placeholder( R.drawable.m_placeholder ).into( binding.posterImage )
        binding.tvTitle.text = show.original_name
        binding.tvTagline.text = show.tagline
        binding.ratingBar.rating = show.vote_average.toFloat() / 2
        binding.tvReleaseDate.text = show.first_air_date
        binding.tvRating.text = show.vote_average.toString()
        binding.tvNumberOfEpisodes.text = "${show.number_of_episodes} episodes"
        binding.tvNumberOfSeasons.text = "${show.number_of_seasons}"
        binding.tvLanguage.text = show.spoken_languages[0].english_name
        binding.tvGenre.text = show.genres[0].name

        if ( show.production_companies.isNotEmpty() ) {
            binding.tvProductionCompany.text =   show.production_companies[0].name
        } else {
            binding.tvProductionCompany.text = "no info"
        }
        if ( show.overview != "") {
            binding.tvOverview.text = show.overview
        } else {
            binding.tvOverview.text = "no info"
        }


        binding.posterImage.setOnClickListener {
            val intent = Intent( this, ArtistImageActivity::class.java )
            intent.putExtra("image_path", "${Constants.BASE_IMAGE_URL}${show.poster_path}" )
            startActivity( intent )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}