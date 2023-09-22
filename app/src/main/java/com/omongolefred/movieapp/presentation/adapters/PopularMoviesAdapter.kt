package com.omongolefred.movieapp.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.databinding.MovieItemViewBinding
import com.omongolefred.movieapp.model.data.movieData.MoviePopular
import com.omongolefred.movieapp.utils.Constants.BASE_IMAGE_URL

class PopularMoviesAdapter : Adapter<PopularMoviesAdapter.MovieViewHolder>() {

    private var itemClick : ItemClick? = null

    fun setUpItemClick( itemClick : ItemClick) {
        this.itemClick = itemClick
    }

    inner class MovieViewHolder( private val binding : MovieItemViewBinding ): ViewHolder( binding.root ) {
        fun bind( movie: MoviePopular  ) {
            Glide.with( binding.root ).load(Uri.parse("${BASE_IMAGE_URL}${movie.poster_path }") ).placeholder( R.drawable.m_placeholder ).into( binding.imageView )
            binding.imageView.setOnClickListener {
                itemClick?.onItemClick( movie.id )
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<MoviePopular >() {
        override fun areItemsTheSame(oldItem : MoviePopular   , newItem : MoviePopular  ) : Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem : MoviePopular   , newItem : MoviePopular  ) : Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer( this, differCallBack )

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MovieViewHolder {
        val binding = MovieItemViewBinding.inflate( LayoutInflater.from( parent.context ), parent, false )
        return MovieViewHolder( binding )
    }

    override fun getItemCount() : Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder : MovieViewHolder , position : Int) {
        val movie = differ.currentList[ position ]
        holder.bind( movie )
    }

    interface ItemClick {
        fun onItemClick( movieId: Int )
    }
}