package com.govardhan.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<Article>>

    @Insert
    fun insertAll(apiArticles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAllAndInsertAll(apiArticles: List<Article>) {
        deleteAll()
        return insertAll(apiArticles)
    }

}