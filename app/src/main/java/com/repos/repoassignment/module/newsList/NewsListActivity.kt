package com.repos.repoassignment.module.newsList

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.Loader
import com.repos.repoassignment.BR
import com.repos.repoassignment.R
import com.repos.repoassignment.module.newsDetail.NewsDetailActivity
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {


    companion object {

        var newsDetailkey = "newsdetail"
    }

    var newsListViewModel = NewsListViewModel()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_news_list)


        /**
         * Data binding
         * */
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this,
            R.layout.activity_news_list)

        /**
         * ViewModel Setup
         * */
        newsListViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(NewsListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, newsListViewModel)




        setObservers()
    }


    /**
     * Observers
     * */
    fun setObservers(){

        newsListViewModel.newsItem.observe(this, Observer{
            if (it){

                val newsDetail = Intent(this, NewsDetailActivity::class.java)
                newsDetail.putExtra(newsDetailkey, newsListViewModel.article)
               // startActivity(newsDetail)

                val activityOptionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, constraint, newsDetailkey)



                startActivity (newsDetail, activityOptionsCompat.toBundle())
            }
        })


        newsListViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showDialog(this)
            } else {
                dismissDialog(this)
            }
        })



    }

    fun showDialog(mContext: Activity) {
        if (progressDialog == null && !mContext.isFinishing) {
            progressDialog = ProgressDialog(mContext)

            progressDialog?.show()
        }
    }

    fun dismissDialog(mContext: Activity) {
        if (progressDialog != null && progressDialog!!.isShowing && !mContext.isFinishing)
            progressDialog?.dismiss()
        progressDialog = null
    }
}