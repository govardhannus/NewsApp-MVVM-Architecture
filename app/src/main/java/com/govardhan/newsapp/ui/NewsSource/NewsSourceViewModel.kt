package com.govardhan.newsapp.ui.NewsSource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govardhan.newsapp.data.model.Source
import com.govardhan.newsapp.data.repository.NewsSourceRepository
import com.govardhan.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourceViewModel (private val newsSourceRepository: NewsSourceRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)

    val uiState : StateFlow<UiState<List<Source>>> = _uiState

    init {
        fetchNewsSource()
    }

    private fun fetchNewsSource(){
        viewModelScope.launch {
            newsSourceRepository.getNewsSource()
                .catch {e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}