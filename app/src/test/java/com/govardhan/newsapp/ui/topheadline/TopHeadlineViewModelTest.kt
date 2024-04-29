package com.govardhan.newsapp.ui.topheadline

import app.cash.turbine.test
import com.govardhan.newsapp.data.model.Article
import com.govardhan.newsapp.data.repository.TopHeadlineRepository
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant.COUNTRY
import com.govardhan.newsapp.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.amitshekhar.newsapp.utils.TestDispatcherProvider
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var topHeadlineRepository: TopHeadlineRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getTopHeadlines(COUNTRY)
            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlineRepository)
                .getTopHeadlines(COUNTRY)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(topHeadlineRepository, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}