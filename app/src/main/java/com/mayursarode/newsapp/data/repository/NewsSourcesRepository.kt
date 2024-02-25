package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.model.ApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(): Flow<List<ApiSource>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.sources
        }
    }
}