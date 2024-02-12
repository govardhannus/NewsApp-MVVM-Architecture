package com.govardhan.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govardhan.newsapp.data.model.Article
import com.govardhan.newsapp.data.repository.SearchRepository
import com.govardhan.newsapp.ui.base.UiState
import com.govardhan.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.govardhan.newsapp.utils.AppConstant.MINI_SEARCH_CHAR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState : StateFlow<UiState<List<Article>>> = _uiState

    private val query = MutableStateFlow("");
    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String){
        query.value = searchQuery
    }

    private fun createNewsFlow(){
        viewModelScope.launch {
            query.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if(it.isNotEmpty() && it.length >= MINI_SEARCH_CHAR ){
                        return@filter true
                    }else{
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest searchRepository.getNewsByQueries(it)
                        .catch { e->
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}