package com.govardhan.newsapp.data.repository

import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourceRepository @Inject constructor(private val networkservice: NetworkService) {

    fun getNewsSource(): Flow<List<Source>> {
        return flow {
            emit(networkservice.getNewsSources())
        }.map {
            it.sources
        }
    }
}