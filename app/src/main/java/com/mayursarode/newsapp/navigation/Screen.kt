package com.mayursarode.newsapp.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen(route = "HomeScreen")

    object TopHeadlinesOnline : Screen(route = "topHeadlineOnline")

    object TopHeadlinesOffline : Screen(route = "topHeadlineOffline")

    object TopHeadlinesPagination : Screen(route = "topHeadlinePagination")

    object NewsSources : Screen(route = "newssources")

    object NewsBySources :
        Screen(route = "newsbysources?sourceId={sourceId}") {
        fun passData(
            sourceId: String = ""
        ): String {
            return "newsbysources?sourceId=$sourceId"
        }
    }

}
