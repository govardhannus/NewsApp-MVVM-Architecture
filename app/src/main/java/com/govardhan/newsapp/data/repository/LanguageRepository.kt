package com.govardhan.newsapp.data.repository

import com.govardhan.newsapp.data.model.Language
import com.govardhan.newsapp.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor() {

    fun getLanguages() : Flow<List<Language>>{
        return flow {
            emit(AppConstant.LANGUAGES)
        }.map {
            it
        }
    }
}