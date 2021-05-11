package com.repos.repoassignment.module.newsDetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.repos.repoassignment.BR
import com.repos.repoassignment.R
import com.repos.repoassignment.module.newsList.NewsListActivity
import com.repos.repoassignment.module.newsList.model.Article
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    var newsDetailViewmodel = NewsDetailViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_news_detail)

        /**
         * Data binding
         * */
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this,
            R.layout.activity_news_detail)

        /**
         * ViewModel Setup
         * */
        newsDetailViewmodel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(NewsDetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, newsDetailViewmodel)


        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val getData = intent

        val data = getData?.getParcelableExtra<Article>(NewsListActivity.newsDetailkey)

        if (data != null) {
            newsDetailViewmodel.article = data
        }


        setObservers()

    }


    /**
     * Observers
     * */
    fun setObservers(){

        newsDetailViewmodel.readMore.observe(this, Observer{

            if (it){

                if (newsDetailViewmodel.urltoOpen.isNotEmpty()){

                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(newsDetailViewmodel.urltoOpen)
                    }
                    startActivity(intent)
                }
            }
        })
    }


}