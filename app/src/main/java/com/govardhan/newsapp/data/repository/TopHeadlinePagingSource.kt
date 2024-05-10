package com.govardhan.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.ApiArticle
import com.govardhan.newsapp.utils.AppConstant.COUNTRY
import com.govardhan.newsapp.utils.AppConstant.INITIAL_PAGE
import com.govardhan.newsapp.utils.AppConstant.PAGE_SIZE

class TopHeadlinePagingSource(private val networkService:NetworkService) :
    PagingSource<Int, ApiArticle>() {
    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
       return try {
             val page = params.key ?: INITIAL_PAGE

           val response = networkService.getTopHeadlinesPaging(
               country = COUNTRY,
               page = page,
               pageSize = PAGE_SIZE
           )

           LoadResult.Page(
               data = response.apiArticles,
               prevKey = if(page == INITIAL_PAGE) null else page.minus(1),
               nextKey = if(response.apiArticles.isEmpty()) null else page.plus(1),
           )

       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}