package com.repos.repoassignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(list: List<NewsListEntity>)

    @Query("SELECT * from news_list_table")
    suspend fun getAllNewsList(): List<NewsListEntity>


    @Query("DELETE  FROM news_list_table")
    suspend fun deleteAllNewsList()
}