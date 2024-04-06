package com.govardhan.newsapp.ui.newssource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onNewsclick : (url : String) -> Unit,
    viewModel : NewsSourceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME)})
    }, content = { padding->
        Column (modifier = Modifier.padding(padding)){
            NewsSourceScreen(uiState,onNewsclick)
        }
    })
}

@Composable
fun NewsSourceScreen(uiState: UiState<List<Source>>, onNewsclick: (url: String) -> Unit){
    when(uiState){
        is UiState.Success -> {
            SourceList(sources = uiState.data, onNewsclick =onNewsclick )
        }

        is UiState.Error -> {
            ShowLoading()
        }

        is UiState.Loading -> {
            ShowError("error")
        }
    }
}

@Composable
fun SourceList(sources:List<Source> , onNewsclick: (url: String) -> Unit){
    LazyColumn {
        items(sources,key = {source -> source.id!! }) { source ->
            Source(source,onNewsclick)
        }
    }
}

@Composable
fun Source(source:Source,onNewsclick: (url: String) -> Unit){
    Column ( modifier = Modifier
        .fillMaxWidth()
    ) {
        Button(onClick = { if (source.url.isNotEmpty()) {
            onNewsclick(source.url)
        } }) {
            Text(text = source.name)
        }
    }
}

