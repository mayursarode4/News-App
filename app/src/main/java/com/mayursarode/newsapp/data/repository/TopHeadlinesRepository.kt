package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.local.database.DatabaseService
import com.mayursarode.newsapp.data.local.entity.Article
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.data.model.toArticleEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlinesRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getTopHeadlinesOnline(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    // Offline
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlinesOffline(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles.map { article -> article.toArticleEntity() }
        }.flatMapConcat { articles ->
            flow { emit(databaseService.deleteAllInsertAll(articles)) }
        }.flatMapConcat {
            databaseService.getArticles()
        }
    }

    fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return databaseService.getArticles()
    }
}