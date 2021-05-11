package com.repos.repoassignment.module.newsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.repos.repoassignment.R
import com.repos.repoassignment.databinding.RowNewsItemBinding
import com.repos.repoassignment.module.newsList.NewsListViewModel

class NewsListAdapter(var viewModel: NewsListViewModel) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    class ViewHolder(val binding : RowNewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =

        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_news_item, parent, false))

    override fun getItemCount(): Int = viewModel.newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = viewModel.newsList[position]

        holder.binding.viewModel = viewModel
        holder.binding.model = model
    }
}