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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.presentation.adapters.PopularShowsAdapter
import com.omongolefred.movieapp.presentation.adapters.TopRatedShowsAdapter
import com.omongolefred.movieapp.databinding.FragmentShowsBinding
import com.omongolefred.movieapp.presentation.ui.activities.ShowDetailActivity
import com.omongolefred.movieapp.presentation.viewModels.ShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowsFragment : Fragment(), TopRatedShowsAdapter.ItemClick, PopularShowsAdapter.ItemClick {

    private var _binding: FragmentShowsBinding? = null
    private val binding
        get()= checkNotNull( _binding )

    private val showsVM by viewModels<ShowsViewModel>()

    private val topRatedShowsAdapter : TopRatedShowsAdapter by lazy {  TopRatedShowsAdapter()  }
    private val popularShowsAdapter : PopularShowsAdapter by lazy {  PopularShowsAdapter()  }

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        _binding = FragmentShowsBinding.inflate( inflater, container, false )
        topRatedShowsAdapter.setUpItemClick( this )
        popularShowsAdapter.setUpItemClick( this )
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                showsVM.topRatedShows.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.topRatedShowsRv.showShimmerAdapter()
                        binding.topRatedShowsRv.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )
                    }else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line) )
                        binding.topRatedShowsRv.hideShimmerAdapter()
                        topRatedShowsAdapter.differ.submitList( it )
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                showsVM.popularShows.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.popularShowsRV.showShimmerAdapter()
                        binding.popularShowsRV.layoutManager = GridLayoutManager( requireContext(), 3 )
                    } else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line) )
                        binding.popularShowsRV.hideShimmerAdapter()
                        popularShowsAdapter.differ.submitList( it )
                    }
                }
            }
        }

        binding.topRatedShowsRv.adapter = topRatedShowsAdapter
        binding.topRatedShowsRv.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )

        binding.popularShowsRV.adapter = popularShowsAdapter
        binding.popularShowsRV.layoutManager = GridLayoutManager( requireContext(), 3 )

    }


    override fun onItemClick(movieId : Int) {
        val intent = Intent( requireContext(), ShowDetailActivity::class.java )
        intent.putExtra("series_id", movieId)
        startActivity( intent )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}