package com.govardhan.newsapp.di.component

import com.govardhan.newsapp.di.ActivityScope
import com.govardhan.newsapp.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

}