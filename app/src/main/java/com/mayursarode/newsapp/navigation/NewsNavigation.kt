package com.mayursarode.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mayursarode.newsapp.ui.HomeScreen
import com.mayursarode.newsapp.ui.base.openCustomChromeTab
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
    }
}