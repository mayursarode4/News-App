package com.mayursarode.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.mayursarode.newsapp.data.local.entity.Article

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
    val source: ApiSource
)

fun ApiArticle.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = source.toEntitySource()
    )
}