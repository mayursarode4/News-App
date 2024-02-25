package com.mayursarode.newsapp.data.api

import com.mayursarode.newsapp.data.model.TopHeadlinesResponse
import com.mayursarode.newsapp.utils.Constants.DEFAULT_COUNTRY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = DEFAULT_COUNTRY): TopHeadlinesResponse
}