package com.mayursarode.newsapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "HomeScreen")
    object TopHeadlinesOnline : Screen(route = "topHeadlineOnline")
    object TopHeadlinesOffline : Screen(route = "topHeadlineOffline")
    object TopHeadlinesPagination : Screen(route = "topHeadlinePagination")


}
