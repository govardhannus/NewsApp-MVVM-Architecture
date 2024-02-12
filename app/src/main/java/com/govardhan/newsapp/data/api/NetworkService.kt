package com.govardhan.newsapp.data.api

import com.govardhan.newsapp.data.model.NewsSourceResponse
import com.govardhan.newsapp.data.model.TopHeadlinesResponse
import com.govardhan.newsapp.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourceResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") search: String): TopHeadlinesResponse



}