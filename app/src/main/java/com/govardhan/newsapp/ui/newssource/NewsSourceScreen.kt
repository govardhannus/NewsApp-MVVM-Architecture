package com.govardhan.newsapp.ui.newssource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.govardhan.newsapp.data.model.Source
import com.govardhan.newsapp.ui.base.ShowError
import com.govardhan.newsapp.ui.base.ShowLoading
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourceRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NewsSourceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSourceScreen(uiState, onNewsClick)
        }
    })
}

@Composable
fun NewsSourceScreen(uiState: UiState<List<Source>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            SourceList(sources = uiState.data, onNewsClick = onNewsClick)
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }
    }
}

@Composable
fun SourceList(sources: List<Source>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(sources, key = { source -> source.id!! }) { source ->
            Source(source, onNewsClick)
        }
    }
}

@Composable
fun Source(source: Source, onNewsClick: (url: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (source.url.isNotEmpty()) {
                onNewsClick(source.url)
            }
        }, shape = RectangleShape, modifier = Modifier.size(width = 340.dp, height = 40.dp)) {
            Text(text = source.name)
        }
    }
}

