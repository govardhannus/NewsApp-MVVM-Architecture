package com.govardhan.newsapp.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govardhan.newsapp.data.model.Language
import com.govardhan.newsapp.data.repository.LanguageRepository
import com.govardhan.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState :StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    private fun fetchLanguages(){
        viewModelScope.launch {
            languageRepository.getLanguages()
                .catch {e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}