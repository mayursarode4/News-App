package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsBySearch(queries: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsBySearch(queries))
        }.map {
            it.articles
        }
    }

}