package com.omongolefred.movieapp.presentation.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.omongolefred.movieapp.R
import com.omongolefred.movieapp.databinding.ActorItemViewBinding
import com.omongolefred.movieapp.model.data.artistData.Actor
import com.omongolefred.movieapp.utils.Constants.BASE_IMAGE_URL

class ArtistAdapter : Adapter<ArtistAdapter.ArtistViewHolder>() {

    private var itemClick : ItemClick? = null

    fun setItemClickListener( itemClick : ItemClick) {
        this.itemClick = itemClick
    }

    inner class ArtistViewHolder( private val binding : ActorItemViewBinding ): ViewHolder( binding.root ) {
        @SuppressLint("SetTextI18n")
        fun bind( actor : Actor) {
            binding.name.text = actor.name
            binding.tvActedIn.text = "Acted in ${actor.known_for[0].title}"
            binding.ratingBar.rating = actor.known_for[0].vote_average.toFloat() / 2
            binding.ratingBar.numStars = 5
            Glide.with( binding.root ).load(Uri.parse("${BASE_IMAGE_URL}${ actor.profile_path }") ).placeholder( R.drawable.m_placeholder ).into( binding.actorsImage )

            binding.actorsImage.setOnClickListener {
                itemClick?.viewArtistFullImage( "${BASE_IMAGE_URL}${ actor.profile_path }" )
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem : Actor , newItem : Actor) : Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem : Actor , newItem : Actor) : Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer( this, differCallBack )

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ArtistViewHolder {
        val binding = ActorItemViewBinding.inflate( LayoutInflater.from( parent.context ), parent, false )
        return ArtistViewHolder( binding )
    }

    override fun getItemCount() : Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder : ArtistViewHolder , position : Int) {
        val actor = differ.currentList[ position ]
        holder.bind( actor )
    }

    interface ItemClick {
        fun viewArtistFullImage( url: String )
    }
}