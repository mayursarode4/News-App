package com.mayursarode.newsapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "HomeScreen")
    object TopHeadlineOnline : Screen(route = "topHeadlineOnline")
}
