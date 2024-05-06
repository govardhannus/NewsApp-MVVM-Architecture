package com.govardhan.newsapp.data.repository

import app.cash.turbine.test
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.model.ApiArticle
import com.govardhan.newsapp.data.model.ApiSource
import com.govardhan.newsapp.data.model.TopHeadlinesResponse
import com.govardhan.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var topHeadlineRepository: TopHeadlineRepository

    @Before
    fun setUp() {
        topHeadlineRepository = TopHeadlineRepository(networkService)
    }

    @Test
    fun getTopHeadlines_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val apiSource = ApiSource(id = "sourceId", name = "sourceName")
            val apiArticle = ApiArticle(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "urlToImage",
                apiSource = apiSource
            )

            val apiArticles = mutableListOf<ApiArticle>()
            apiArticles.add(apiArticle)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, apiArticles = apiArticles
            )

            doReturn(topHeadlinesResponse).`when`(networkService).getTopHeadlines(COUNTRY)

            topHeadlineRepository.getTopHeadlines(COUNTRY).test {
                assertEquals(topHeadlinesResponse.apiArticles, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(networkService, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun getTopHeadlines_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            doThrow(RuntimeException(errorMessage)).`when`(networkService).getTopHeadlines(COUNTRY)

            topHeadlineRepository.getTopHeadlines(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getTopHeadlines(COUNTRY)
        }
    }

}