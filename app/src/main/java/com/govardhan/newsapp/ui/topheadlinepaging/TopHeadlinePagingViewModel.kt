package com.govardhan.newsapp.ui.topheadlinepaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.govardhan.newsapp.data.model.ApiArticle
import com.govardhan.newsapp.data.repository.TopHeadlinePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinePagingViewModel @Inject constructor(private val topHeadlinePagingRepository: TopHeadlinePagingRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<ApiArticle>> = _uiState

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            topHeadlinePagingRepository.getTopHeadlinePaging()
                .collect {
                    _uiState.value = it
                }
        }
    }
}