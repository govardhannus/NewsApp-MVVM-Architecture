package com.govardhan.newsapp.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.govardhan.newsapp.data.api.NetworkService
import com.govardhan.newsapp.data.local.DatabaseService
import com.govardhan.newsapp.data.model.apiArticleListToArticleList
import com.govardhan.newsapp.utils.AppConstant.COUNTRY
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val database: DatabaseService,
    private val network: NetworkService
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        lateinit var result: Result
        kotlin.runCatching {
            val articles =
                network.getTopHeadlines(COUNTRY).apiArticles.apiArticleListToArticleList()
            database.deleteAllAndInsertAll(articles)
        }.onSuccess {
            result = Result.success()
        }.onFailure {
            result = Result.failure()
        }
        return result
    }
}