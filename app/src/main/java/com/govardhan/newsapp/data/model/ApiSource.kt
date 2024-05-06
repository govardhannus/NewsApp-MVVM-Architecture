package com.govardhan.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.govardhan.newsapp.data.local.entity.Source

data class ApiSource (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("language")
    val language: String = "",
    @SerializedName("country")
    val country: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    return Source(
        id=id,
        name=name
    )
}