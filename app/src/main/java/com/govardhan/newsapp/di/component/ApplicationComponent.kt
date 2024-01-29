package com.govardhan.newsapp.di.component

import android.content.Context
import com.govardhan.newsapp.NewsApplication
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.repository.NewsSourceRepository
import com.govardhan.newsapp.data.repository.TopHeadlineRepository
import com.govardhan.newsapp.di.ApplicationContext
import com.govardhan.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourceRepository(): NewsSourceRepository

}