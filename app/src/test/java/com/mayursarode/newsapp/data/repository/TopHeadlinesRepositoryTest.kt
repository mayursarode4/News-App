package com.mayursarode.newsapp.data.repository

import app.cash.turbine.test
import com.mayursarode.newsapp.data.api.NetworkService
import com.mayursarode.newsapp.data.local.database.DatabaseService
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.data.model.ApiSource
import com.mayursarode.newsapp.data.model.TopHeadlinesResponse
import com.mayursarode.newsapp.utils.Constants.DEFAULT_COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    @Mock
    private lateinit var databaseService: DatabaseService

    private lateinit var topHeadlinesRepository: TopHeadlinesRepository

    @Before
    fun setup() {
        topHeadlinesRepository = TopHeadlinesRepository(networkService, databaseService)
    }

    @Test
    fun getTopHeadlinesOnline_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val source = ApiSource(id = "sourceId", name = "sourceName1")
            val article = ApiArticle(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "imageUrl",
                source = source
            )

            val articles = mutableListOf<ApiArticle>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articles = articles
            )

            doReturn(topHeadlinesResponse).`when`(networkService).getTopHeadlines(DEFAULT_COUNTRY)

            topHeadlinesRepository.getTopHeadlinesOnline(DEFAULT_COUNTRY).test {
                assertEquals(topHeadlinesResponse.articles, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(networkService, times(1)).getTopHeadlines(DEFAULT_COUNTRY)

        }
    }

    @Test
    fun getTopHeadlinesOnline_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message"
            doThrow(RuntimeException(errorMessage)).`when`(networkService).getTopHeadlines(
                DEFAULT_COUNTRY
            )
            topHeadlinesRepository.getTopHeadlinesOnline(DEFAULT_COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            verify(networkService, times(1)).getTopHeadlines(DEFAULT_COUNTRY)
        }
    }

    @After
    fun tearDown() {

    }
}