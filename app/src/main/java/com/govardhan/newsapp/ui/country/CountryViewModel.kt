package com.govardhan.newsapp.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govardhan.newsapp.data.model.Country
import com.govardhan.newsapp.data.repository.CountryRepository
import com.govardhan.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel (private val countryRepository: CountryRepository): ViewModel(){

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val uiState :StateFlow<UiState<List<Country>>> = _uiState

    init {
        fetchCountry()
    }

    private fun fetchCountry(){
        viewModelScope.launch {
            countryRepository.getCountry()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect{
                    _uiState.value = UiState.Success(it)
                }

        }
    }
}