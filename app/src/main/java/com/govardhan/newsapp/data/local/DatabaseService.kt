package com.govardhan.newsapp.data.local

import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(apiArticles: List<Article>)

}