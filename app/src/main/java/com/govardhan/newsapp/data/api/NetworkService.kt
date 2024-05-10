package com.govardhan.newsapp.data.api

import com.govardhan.newsapp.data.model.NewsSourceResponse
import com.govardhan.newsapp.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourceResponse

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") search: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesPaging(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResponse

}