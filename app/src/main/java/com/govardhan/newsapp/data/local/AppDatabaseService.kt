package com.govardhan.newsapp.data.local

import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow

class AppDatabaseService constructor(private val appDatabase: AppDatabase) : DatabaseService {

    override fun getArticles(): Flow<List<Article>> {
        return appDatabase.articleDao().getAll()
    }

    override fun deleteAllAndInsertAll(apiArticles: List<Article>) {
        appDatabase.articleDao().deleteAllAndInsertAll(apiArticles)
    }

}