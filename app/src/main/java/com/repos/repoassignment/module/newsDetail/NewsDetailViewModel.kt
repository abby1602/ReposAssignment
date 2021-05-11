package com.repos.repoassignment.module.newsDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.repos.repoassignment.module.newsList.model.Article

class NewsDetailViewModel : ViewModel() {

    var article = Article()

    var readMore = MutableLiveData(false)

    var urltoOpen = ""



    /**
     * Read more click to open article in chrome
     * */
    fun readMoreClick(url : String){

        urltoOpen = url

        readMore.value = true
    }
}