package com.omongolefred.movieapp.presentation.ui.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.databinding.ActivityArtistImageBinding
import com.omongolefred.movieapp.utils.Constants

class ArtistImageActivity : AppCompatActivity() {

    private var _binding: ActivityArtistImageBinding? = null
    private val binding
        get() = checkNotNull( _binding )

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme( R.style.FullImageActivity )
        _binding = ActivityArtistImageBinding.inflate( layoutInflater )
        setContentView( binding.root )

        val imagePath = intent.getStringExtra("image_path")
        Glide.with( binding.root ).load(Uri.parse( imagePath ) ).into( binding.root )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}