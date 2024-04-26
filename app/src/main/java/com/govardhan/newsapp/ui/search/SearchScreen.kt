package com.govardhan.newsapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.govardhan.newsapp.data.model.Article
import com.govardhan.newsapp.ui.base.Article
import com.govardhan.newsapp.ui.base.ShowError
import com.govardhan.newsapp.ui.base.ShowLoading
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRoute(
    onNewsClick: (url : String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchText by viewModel.query.collectAsStateWithLifecycle()

    Scaffold (topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding->
        Column (modifier = Modifier.padding(padding)){
            SearchScreen(uiState,viewModel,onNewsClick,searchText)
        }
    })
}
@Composable
fun SearchScreen(
    uiState: UiState<List<Article>>,
    viewModel: SearchViewModel,
    onNewsClick: (url: String) -> Unit,
    searchText: String
){
    when(uiState){
        is UiState.Success -> {
            Search(articles = uiState.data,viewModel,onNewsClick,searchText)
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(articles:List<Article>,viewModel: SearchViewModel,onNewsClick: (url: String) -> Unit,searchText: String){
    Column (modifier = Modifier.fillMaxSize()){
           TextField(value = searchText,
               onValueChange = viewModel::searchNews,
               modifier = Modifier.fillMaxWidth(),
               placeholder = { Text(text = "Search")}
           )
           Spacer(modifier = Modifier.height(16.dp))
           LazyColumn {
            items(articles,key = {article -> article.url }) { article ->
                Article(article,onNewsClick)
            }
        }
    }
}
