package com.govardhan.newsapp.ui.topheadlinepaging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.govardhan.newsapp.data.model.ApiArticle
import com.govardhan.newsapp.ui.base.Article
import com.govardhan.newsapp.ui.base.ShowError
import com.govardhan.newsapp.ui.base.ShowLoading
import com.govardhan.newsapp.ui.offlinearticle.ArticleList
import com.govardhan.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlinePagingRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlinePagingViewModel = hiltViewModel()
) {
    val articles = viewModel.uiState.collectAsLazyPagingItems()

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlinePagingScreen(articles, onNewsClick)
        }
    })

}

@Composable
fun TopHeadlinePagingScreen(articles: LazyPagingItems<ApiArticle>, onNewsClick: (url: String) -> Unit) {

    ArticleList(articles, onNewsClick)

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }

            loadState.append is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.append is LoadState.Error -> {
                val error = articles.loadState.append as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }
        }
    }

}

@Composable
fun ArticleList(articles: LazyPagingItems<ApiArticle>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]!!.url }) { index ->
            Article(articles[index]!!, onNewsClick)
        }
    }
}