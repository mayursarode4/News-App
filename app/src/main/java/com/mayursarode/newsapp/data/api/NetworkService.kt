package com.mayursarode.newsapp.data.api

import com.mayursarode.newsapp.data.model.NewsSourcesResponse
import com.mayursarode.newsapp.data.model.TopHeadlinesResponse
import com.mayursarode.newsapp.utils.Constants.DEFAULT_COUNTRY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = DEFAULT_COUNTRY): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesPaging(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesResponse

}