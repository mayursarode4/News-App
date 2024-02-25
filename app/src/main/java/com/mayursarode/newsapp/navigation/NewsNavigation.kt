package com.mayursarode.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mayursarode.newsapp.ui.HomeScreen
import com.mayursarode.newsapp.ui.base.openCustomChromeTab
import com.mayursarode.newsapp.ui.country.CountryListRoute
import com.mayursarode.newsapp.ui.language.LanguageListRoute
import com.mayursarode.newsapp.ui.newsbysources.NewsBySourcesRoute
import com.mayursarode.newsapp.ui.newssources.NewsSourcesRoute
import com.mayursarode.newsapp.ui.search.SearchScreenRoute
import com.mayursarode.newsapp.ui.topheadlines.offline.TopHeadlinesOfflineRoute
import com.mayursarode.newsapp.ui.topheadlines.online.TopHeadlinesOnlineRoute
import com.mayursarode.newsapp.ui.topheadlines.paging.TopHeadlinesPagingRoute

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.TopHeadlinesOnline.route
        ) {
            TopHeadlinesOnlineRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                })
        }

        composable(
            route = Screen.TopHeadlinesOffline.route
        ) {
            TopHeadlinesOfflineRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                })
        }

        composable(
            route = Screen.TopHeadlinesPagination.route
        ) {
            TopHeadlinesPagingRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                })
        }

        composable(
            route = Screen.NewsSources.route
        ) {
            NewsSourcesRoute(navController = navController)
        }

        composable(
            route = Screen.NewsBySources.route,
            arguments = listOf(
                navArgument("sourceId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("countryCode") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("languageId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { it ->

            val sourceId = it.arguments?.getString("sourceId").toString()
            val countryCode = it.arguments?.getString("countryCode").toString()
            val languageId = it.arguments?.getString("languageId").toString()

            NewsBySourcesRoute(
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }, sourceId = sourceId, countryCode = countryCode, languageId = languageId
            )
        }

        composable(
            route = Screen.Countries.route
        ) {
            CountryListRoute(navController = navController)
        }

        composable(
            route = Screen.Languages.route
        ) {
            LanguageListRoute(navController = navController)

        }

        composable(
            route = Screen.SearchNews.route
        ) {
            SearchScreenRoute(
                navController = navController,
                onNewsClick = {
                    openCustomChromeTab(context, it)
                })
        }
    }
}