package com.govardhan.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.govardhan.newsapp.data.local.entity.Article

data class ApiArticle(

    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val apiSource: ApiSource
)

fun ApiArticle.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = apiSource.toSourceEntity()
    )
}

fun List<ApiArticle>.apiArticleListToArticleList(): List<Article> {
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.toArticleEntity())
    }
    return list
}

