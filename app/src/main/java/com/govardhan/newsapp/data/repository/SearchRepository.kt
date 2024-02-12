package com.govardhan.newsapp.data.repository

import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsByQueries(sources: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByQueries(sources))
        }.map {
            it.articles
        }
    }
}