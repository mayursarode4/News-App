package com.mayursarode.newsapp.utils

import com.mayursarode.newsapp.BuildConfig

object Constants {

    const val APP_NAME = "News-App"
    const val DEFAULT_COUNTRY = "in"  // India
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://newsapi.org/v2/"

    // screen name
    const val TOP_HEADLINES_ONLINE = "Top Headlines Online"
    const val TOP_HEADLINES_OFFLINE = "Top Headlines Offline"
    const val TOP_HEADLINES_PAGING = "Top Headlines Pagination"
    const val NEWS_SOURCES = "News Sources"
    const val COUNTRIES_SOURCES = "Select a Country"
    const val LANGUAGE_SOURCES = "Select a Language"
    const val SEARCH_NEWS = "Search News"

    // PAGING
    const val INITIAL_PAGE = 1
    const val PAGE_SIZE = 10

    // Search
    const val DEBOUNCE_TIMEOUT = 300L
    const val MIN_SEARCH_CHAR = 3

}
