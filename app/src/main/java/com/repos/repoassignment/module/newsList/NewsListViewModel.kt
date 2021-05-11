package com.repos.repoassignment.module.newsList

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.repos.repoassignment.module.newsList.adapter.NewsListAdapter
import com.repos.repoassignment.module.newsList.model.Article
import com.repos.repoassignment.module.newsList.model.NewsListResponse
import com.repos.repoassignment.room.LocalDatabase
import com.repos.repoassignment.room.NewsListEntity
import com.repos.repoassignment.utils.ApiClient
import com.repos.repoassignment.utils.ApiInterface
import com.repos.repoassignment.utils.App
import com.repos.repoassignment.utils.NetworkAvailability
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NewsListViewModel : ViewModel() {


    var apiclient = ApiClient.getClient().create(ApiInterface::class.java)
    var isLoading = MutableLiveData<Boolean>()

    var newsList = ArrayList<Article>()
    var newsAdapter = NewsListAdapter(this)

    var newsItem = MutableLiveData(false)

    var article = Article()

    var noDataAvailable = MutableLiveData(View.GONE)
    var rvVisibility = MutableLiveData(View.VISIBLE)

    init {
        getNewsList()
    }

    /**
     * Fetch news list from server
     * */
    fun getNewsList(){

        if(NetworkAvailability.isNetworkAvailable()){

            isLoading.value = true

            apiclient.getNewsList().enqueue(object : Callback<NewsListResponse>{
                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {

                    isLoading.value = false

                }

                override fun onResponse(call: Call<NewsListResponse>, response: Response<NewsListResponse>) {

                    isLoading.value = false

                    val body = response.body()

                    if (response.isSuccessful){


                        if (body?.articles?.isEmpty()!!){

                            noDataAvailable.value = View.VISIBLE
                            rvVisibility.value = View.INVISIBLE

                        }else{

                            body.articles.let { newsList.addAll(it) }
                            newsAdapter.notifyDataSetChanged()


                            val tempList= ArrayList<NewsListEntity>()

                            body.articles.forEach {

                                val strobj = App.instance.gson.toJson(it).toString()
                                tempList.add(App.instance.gson.fromJson(strobj, NewsListEntity::class.java))
                            }

                            viewModelScope.launch {

                                LocalDatabase.getDatabase().newsListDAO().deleteAllNewsList()
                                LocalDatabase.getDatabase().newsListDAO().insertNewsList(tempList)
                            }

                            Log.e("templist", tempList.toString())
                        }



                    }
                }
            })

        }else{

            viewModelScope.launch {

                val localnewsList = LocalDatabase.getDatabase().newsListDAO().getAllNewsList()

                localnewsList.forEach {

                    newsList.add(
                        App.instance.gson.fromJson(App.instance.gson.toJson(it).toString(),
                        Article::class.java))
                }

                newsAdapter.notifyDataSetChanged()
            }

        }


    }


    /**
     * News Item Click
     * */
    fun newsItemClick(model: Article){

        article = model
        newsItem.value = true

    }


}