package com.example.batmanproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.batmanproject.Models.DetailMovie.DetailMovie
import com.example.batmanproject.R
import kotlinx.android.synthetic.main.item_movie.view.*

class SelectAdapter : RecyclerView.Adapter<SelectAdapter.SelectViewHolder> (){

    inner class SelectViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<DetailMovie>(){
        override fun areItemsTheSame(oldItem: DetailMovie, newItem: DetailMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DetailMovie, newItem: DetailMovie): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        return SelectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.select_items,
                parent,
                false
            )
        )
    }

    private var onItemClickListener : ((DetailMovie) -> Unit) ?= null


    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        val detailMovie = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(detailMovie.Poster).into(ivArticleImage)
            tvSource.text = detailMovie.Type
            tvTitle.text = detailMovie.Title
            tvDescription.text = detailMovie.Year
            tvPublishedAt.text = detailMovie.imdbID


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}