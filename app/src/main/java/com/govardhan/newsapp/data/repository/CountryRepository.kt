package com.govardhan.newsapp.data.repository

import android.provider.SyncStateContract.Constants
import com.govardhan.newsapp.data.model.Country
import com.govardhan.newsapp.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(){

    fun getCountry(): Flow<List<Country>>{
        return flow {
            emit(AppConstant.COUNTRIES)
        }
    }
}