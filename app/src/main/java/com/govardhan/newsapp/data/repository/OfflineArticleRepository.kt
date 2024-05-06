package com.govardhan.newsapp.data.repository

import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.local.DatabaseService
import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.model.toArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineArticleRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getArticles(country: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .map {
                it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll(articles)) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return databaseService.getArticles()
    }

}
