package com.repos.repoassignment.room

import androidx.room.*
import com.repos.repoassignment.module.newsList.model.Source

@Entity(tableName = "news_list_table")
data class NewsListEntity (

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "primary") var primary :Int = 0,
    @ColumnInfo(name = "author") var author: String? = "",
    @ColumnInfo(name = "content") var content: String? = "",
    @ColumnInfo(name = "description") var description: String? = "",
    @ColumnInfo(name = "publishedAt")   var publishedAt: String? = "",

    @Embedded var source: Source? = Source(),
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "url")  var url: String? = "",
    @ColumnInfo(name = "urlToImage") var urlToImage: String? = ""
)