package com.repos.repoassignment.utils

import com.repos.repoassignment.module.newsList.model.NewsListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("top-headlines?country=us&apiKey=6d62877ddf5b4b7bb89437ebe99e7d34")
    fun getNewsList() : Call<NewsListResponse>
}