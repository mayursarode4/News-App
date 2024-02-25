package com.mayursarode.newsapp.data.local.database

import com.mayursarode.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getArticles(): Flow<List<Article>>
    fun deleteAllInsertAll(articles: List<Article>)

}