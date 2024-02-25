package com.mayursarode.newsapp.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen(route = "HomeScreen")

    object TopHeadlinesOnline : Screen(route = "topHeadlineOnline")

    object TopHeadlinesOffline : Screen(route = "topHeadlineOffline")

    object TopHeadlinesPagination : Screen(route = "topHeadlinePagination")

    object NewsSources : Screen(route = "newssources")

    object NewsBySources :
        Screen(route = "newsbysources?sourceId={sourceId}&countryCode={countryCode}") {
        fun passData(
            sourceId: String = "",
            countryCode: String = ""
        ): String {
            return "newsbysources?sourceId=$sourceId&countryCode=$countryCode"
        }
    }

    object Countries : Screen(route = "countries")


}
