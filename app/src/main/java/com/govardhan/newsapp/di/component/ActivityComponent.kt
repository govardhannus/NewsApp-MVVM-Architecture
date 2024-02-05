package com.govardhan.newsapp.di.component

import com.govardhan.newsapp.di.ActivityScope
import com.govardhan.newsapp.di.module.ActivityModule
import com.govardhan.newsapp.ui.NewsSource.NewsSourceActivity
import com.govardhan.newsapp.ui.country.CountryActivity
import com.govardhan.newsapp.ui.language.LanguageActivity
import com.govardhan.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

    fun inject(activity: NewsSourceActivity)

    fun inject(activity: CountryActivity)

    fun inject(activity: LanguageActivity)

}