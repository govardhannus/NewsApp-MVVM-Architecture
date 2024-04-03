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

    private val _uistate = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState :StateFlow<UiState<List<Language>>> = _uistate

    init {
         getCuntries()
    }

    private fun getCuntries(){
        viewModelScope.launch {
            languageRepository.getLanguages()
                .catch {e ->
                    _uistate.value = UiState.Error(e.toString())
                }.collect{
                    _uistate.value = UiState.Success(it)
                }
        }
    }
}