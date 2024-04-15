package com.govardhan.newsapp.ui.topheadline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.govardhan.newsapp.data.model.Article
import com.govardhan.newsapp.ui.base.ArticeleList
import com.govardhan.newsapp.ui.base.ShowError
import com.govardhan.newsapp.ui.base.ShowLoading
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineRoute(
    onNewsclick: (url : String) ->     Unit,
            viewModel: TopHeadlineViewModel = hiltViewModel()
){
          val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME)})
    }, content = { padding->
        Column (modifier = Modifier.padding(padding)){
            TopHeadlineScreen(uiState,onNewsclick)
        }
    })
}

@Composable
fun TopHeadlineScreen(uiState: UiState<List<Article>>, onNewsclick: (url: String) -> Unit){
    when(uiState){
        is UiState.Success -> {
            ArticeleList(articles = uiState.data, onNewsclick =onNewsclick )
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }
    }
}

