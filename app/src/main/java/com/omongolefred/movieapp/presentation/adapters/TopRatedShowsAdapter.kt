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
import com.omongolefred.movieapp.model.data.showData.ShowTopRated
import com.omongolefred.movieapp.utils.Constants.BASE_IMAGE_URL

class TopRatedShowsAdapter : Adapter<TopRatedShowsAdapter.ShowViewHolder>() {

    private var itemClick : ItemClick? = null

    fun setUpItemClick( itemClick : ItemClick) {
        this.itemClick = itemClick
    }


    inner class ShowViewHolder( private val binding : MovieItemViewBinding ): ViewHolder( binding.root ) {
        fun bind( show: ShowTopRated ) {
            Glide.with( binding.root ).load(Uri.parse("${BASE_IMAGE_URL}${ show.poster_path }") ).placeholder( R.drawable.m_placeholder ).into( binding.imageView )
            binding.imageView.setOnClickListener {
                itemClick?.onItemClick( show.id )
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ShowTopRated>() {
        override fun areItemsTheSame(oldItem : ShowTopRated , newItem : ShowTopRated ) : Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame( oldItem : ShowTopRated , newItem : ShowTopRated ) : Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer( this, differCallBack )

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ShowViewHolder {
        val binding = MovieItemViewBinding.inflate( LayoutInflater.from( parent.context ), parent, false )
        return ShowViewHolder( binding )
    }

    override fun getItemCount() : Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder : ShowViewHolder , position : Int) {
        val show = differ.currentList[ position ]
        holder.bind( show )
    }

    interface ItemClick {
        fun onItemClick( movieId: Int )
    }
}