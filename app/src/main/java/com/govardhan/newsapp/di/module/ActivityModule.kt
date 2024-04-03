package com.govardhan.newsapp.di.module

import com.govardhan.newsapp.ui.newssource.NewsSourceAdapter
import com.govardhan.newsapp.ui.country.CountryAdapter
import com.govardhan.newsapp.ui.language.LanguageAdapter
import com.govardhan.newsapp.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule() {

    @ActivityScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideNewsSourceAdapter() = NewsSourceAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideCountryAdapter() = CountryAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())

}