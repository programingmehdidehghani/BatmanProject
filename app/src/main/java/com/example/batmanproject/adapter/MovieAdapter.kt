package com.example.batmanproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.batmanproject.Models.Search
import com.example.batmanproject.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Search>(){
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.Title == newItem.Title
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Search) -> Unit) ?= null


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val search = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(search.Poster).into(ivArticleImage)
            tvSource.text = search.Type
            tvTitle.text = search.Title
            tvDescription.text = search.Year
            tvPublishedAt.text = search.imdbID
            setOnClickListener {
                onItemClickListener?.let { it(search) }
            }

        }
    }


    fun setOnItemClickListener(listener : (Search) -> Unit) {
        onItemClickListener = listener
    }

}