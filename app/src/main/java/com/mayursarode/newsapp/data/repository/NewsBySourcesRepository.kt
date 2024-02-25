package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsBySourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsBySources(sources: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsBySources(sources))
        }.map {
            it.articles
        }
    }

    fun getNewsByCountry(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByCountry(country))
        }.map {
            it.articles
        }
    }

    fun getNewsByLanguage(language: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByLanguage(language))
        }.map {
            it.articles
        }
    }

}