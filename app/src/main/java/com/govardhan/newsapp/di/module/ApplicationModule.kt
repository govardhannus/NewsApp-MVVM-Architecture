package com.govardhan.newsapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.govardhan.newsapp.data.api.ApiKeyInterceptor
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.local.AppDatabase
import com.govardhan.newsapp.data.local.AppDatabaseService
import com.govardhan.newsapp.data.local.DatabaseService
import com.govardhan.newsapp.di.BaseUrl
import com.govardhan.newsapp.di.DatabaseName
import com.govardhan.newsapp.di.NetworkApiKey
import com.govardhan.newsapp.utils.DefaultDispatcherProvider
import com.govardhan.newsapp.utils.DefaultNetworkHelper
import com.govardhan.newsapp.utils.DispatcherProvider
import com.govardhan.newsapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = "9f6482a584804376874b848980b7a044"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper{
        return DefaultNetworkHelper(context)
    }


    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "news-database"

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: AppDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }
}