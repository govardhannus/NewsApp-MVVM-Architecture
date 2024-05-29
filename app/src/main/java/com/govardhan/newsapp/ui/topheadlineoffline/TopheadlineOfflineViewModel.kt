package com.govardhan.newsapp.ui.topheadlineoffline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.repository.OfflineArticleRepository
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant.COUNTRY
import com.govardhan.newsapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TopheadlineOfflineViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    private val offlineArticleRepository: OfflineArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
             fetchArticles()
        }else{
            fetchArticlesDirectlyFromDB()
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            offlineArticleRepository.getArticles(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }

        }
    }

    private fun fetchArticlesDirectlyFromDB() {
        viewModelScope.launch {
            offlineArticleRepository.getArticlesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }

        }
    }
}