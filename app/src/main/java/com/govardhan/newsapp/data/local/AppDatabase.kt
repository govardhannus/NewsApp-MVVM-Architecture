package com.govardhan.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.govardhan.newsapp.data.local.dao.ArticleDao
import com.govardhan.newsapp.data.local.entity.Article
import com.govardhan.newsapp.data.model.ApiArticle

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}