package com.omongolefred.movieapp.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.presentation.adapters.ArtistAdapter
import com.omongolefred.movieapp.databinding.FragmentActorsBinding
import com.omongolefred.movieapp.presentation.ui.activities.ArtistImageActivity
import com.omongolefred.movieapp.presentation.viewModels.ActorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActorsFragment : Fragment(), ArtistAdapter.ItemClick {

    private var _binding: FragmentActorsBinding? = null
    private val binding
        get()= checkNotNull( _binding )

    private val actorsVM by viewModels<ActorsViewModel>()

    private val artistAdapter : ArtistAdapter by lazy { ArtistAdapter() }

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        _binding = FragmentActorsBinding.inflate( inflater, container, false )
        artistAdapter.setItemClickListener( this )
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                actorsVM.actors.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.artistsRv.showShimmerAdapter()
                        binding.artistsRv.layoutManager = GridLayoutManager(requireContext(), 2 )
                    } else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line ) )
                        binding.artistsRv.hideShimmerAdapter()
                        artistAdapter.differ.submitList( it )
                    }
                }
            }
        }

        binding.artistsRv.adapter = artistAdapter
        binding.artistsRv.layoutManager = GridLayoutManager(requireContext(), 2 )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun viewArtistFullImage( url : String ) {
        val intent = Intent( requireContext(), ArtistImageActivity::class.java )
        intent.putExtra("image_path", url )
        startActivity( intent )
    }

}