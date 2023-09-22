package com.omongolefred.movieapp.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.presentation.adapters.PopularMoviesAdapter
import com.omongolefred.movieapp.presentation.adapters.TopRatedMoviesAdapter
import com.omongolefred.movieapp.presentation.adapters.UpComingMoviesAdapter
import com.omongolefred.movieapp.databinding.FragmentMoviesBinding
import com.omongolefred.movieapp.presentation.ui.activities.MovieDetailActivity
import com.omongolefred.movieapp.presentation.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), TopRatedMoviesAdapter.ItemClick, PopularMoviesAdapter.ItemClick, UpComingMoviesAdapter.ItemClick {

    private var _binding: FragmentMoviesBinding? = null
    private val binding
        get()= checkNotNull( _binding )

    private val movieVM by viewModels<MoviesViewModel>()

    private val upcomingMoviesAdapter : UpComingMoviesAdapter by lazy { UpComingMoviesAdapter() }
    private val topRatedMoviesAdapter : TopRatedMoviesAdapter by lazy { TopRatedMoviesAdapter() }
    private val popularMoviesAdapter : PopularMoviesAdapter by lazy { PopularMoviesAdapter() }

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        _binding = FragmentMoviesBinding.inflate( inflater, container, false )
        ( activity as AppCompatActivity ).setSupportActionBar( binding.toolbar )
        upcomingMoviesAdapter.setUpItemClick( this )
        topRatedMoviesAdapter.setUpItemClick( this )
        popularMoviesAdapter.setUpItemClick( this )
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                movieVM.upComingMovies.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.upcomingMoviesRv.showShimmerAdapter()
                        binding.upcomingMoviesRv.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )
                    } else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line ) )
                        binding.upcomingMoviesRv.hideShimmerAdapter()
                        upcomingMoviesAdapter.differ.submitList( it )
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                movieVM.topRatedMovies.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.topRatedMoviesRV.showShimmerAdapter()
                        binding.topRatedMoviesRV.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )
                    } else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line ) )
                        binding.topRatedMoviesRV.hideShimmerAdapter()
                        topRatedMoviesAdapter.differ.submitList( it )
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle( Lifecycle.State.STARTED ) {
                movieVM.popularMovies.collect{
                    if ( it.isEmpty() ) {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.white ) )
                        binding.popularMoviesRV.showShimmerAdapter()
                        binding.popularMoviesRV.layoutManager = GridLayoutManager( requireContext(), 3 )
                    } else {
                        binding.root.setBackgroundColor( ContextCompat.getColor( requireActivity(), R.color.g_line ) )
                        binding.popularMoviesRV.hideShimmerAdapter()
                        popularMoviesAdapter.differ.submitList( it )
                    }
                }
            }
        }

        binding.upcomingMoviesRv.adapter = upcomingMoviesAdapter
        binding.upcomingMoviesRv.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )

        binding.topRatedMoviesRV.adapter = topRatedMoviesAdapter
        binding.topRatedMoviesRV.layoutManager = LinearLayoutManager( requireContext(), HORIZONTAL, false )

        binding.popularMoviesRV.adapter = popularMoviesAdapter
        binding.popularMoviesRV.layoutManager = GridLayoutManager( requireContext(), 3 )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movieId : Int) {
        val intent = Intent( requireContext(), MovieDetailActivity::class.java )
        intent.putExtra("movie_id", movieId)
        startActivity( intent )
    }

}