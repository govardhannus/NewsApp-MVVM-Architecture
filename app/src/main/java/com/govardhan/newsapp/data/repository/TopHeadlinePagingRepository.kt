package com.govardhan.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.ApiArticle
import com.govardhan.newsapp.utils.AppConstant.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinePagingRepository @Inject constructor(private val networkService: NetworkService){

    fun getTopHeadlinePaging() : Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                TopHeadlinePagingSource(networkService)
            }
        ).flow
    }
}