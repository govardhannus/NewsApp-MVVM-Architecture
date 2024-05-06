package com.govardhan.newsapp.data.repository

import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsByQueries(sources: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByQueries(sources))
        }.map {
            it.apiArticles
        }
    }
}