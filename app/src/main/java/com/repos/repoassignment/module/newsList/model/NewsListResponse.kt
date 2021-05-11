package com.repos.repoassignment.module.newsList.model

data class NewsListResponse(
    var articles: List<Article> = listOf(),
    var status: String? = "",
    var totalResults: Int? = 0
)