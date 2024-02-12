package com.govardhan.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.govardhan.newsapp.data.repository.CountryRepository
import com.govardhan.newsapp.data.repository.LanguageRepository
import com.govardhan.newsapp.data.repository.NewsSourceRepository
import com.govardhan.newsapp.data.repository.SearchRepository
import com.govardhan.newsapp.data.repository.TopHeadlineRepository
import com.govardhan.newsapp.di.ActivityContext
import com.govardhan.newsapp.ui.NewsSource.NewsSourceAdapter
import com.govardhan.newsapp.ui.NewsSource.NewsSourceViewModel
import com.govardhan.newsapp.ui.base.ViewModelProviderFactory
import com.govardhan.newsapp.ui.country.CountryAdapter
import com.govardhan.newsapp.ui.country.CountryViewModel
import com.govardhan.newsapp.ui.language.LanguageAdapter
import com.govardhan.newsapp.ui.language.LanguageViewModel
import com.govardhan.newsapp.ui.search.SearchViewModel
import com.govardhan.newsapp.ui.topheadline.TopHeadlineAdapter
import com.govardhan.newsapp.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourceRepository): NewsSourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourceViewModel::class) {
                NewsSourceViewModel(newsSourceRepository)
            })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideNewsSourceAdapter() = NewsSourceAdapter(ArrayList())

    @Provides
    fun provideCountryViewModel(countryRepository: CountryRepository): CountryViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountryViewModel::class) {
                CountryViewModel(countryRepository)
            })[CountryViewModel::class.java]
    }
    @Provides
    fun provideCountryAdapter() = CountryAdapter(ArrayList())

    @Provides
    fun provideLanguageViewModel(languageRepository: LanguageRepository): LanguageViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(LanguageViewModel::class) {
                LanguageViewModel(languageRepository)
            })[LanguageViewModel::class.java]
    }
    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())

    @Provides
    fun provideSearchViewModel(searchRepository: SearchRepository): SearchViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(searchRepository)
            })[SearchViewModel::class.java]
    }
}