package com.mayursarode.newsapp.ui.topheadlines.online

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.ui.base.ArticleList
import com.mayursarode.newsapp.ui.base.ShowError
import com.mayursarode.newsapp.ui.base.ShowLoading
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.ui.base.topAppBar
import com.mayursarode.newsapp.utils.Constants

@Composable
fun TopHeadlinesOnlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlinesOnlineViewModel = hiltViewModel(),
    navController: NavHostController
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.TOP_HEADLINES_ONLINE, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            TopHeadlinesOnlineScreen(padding, uiState, onNewsClick) {
                viewModel.fetchNewsOnline()
            }

        }
    )
}

@Composable
fun TopHeadlinesOnlineScreen(
    padding: PaddingValues,
    uiState: UiState<List<ApiArticle>>,
    onNewsClick: (url: String) -> Unit,
    onRetryClick: () -> Unit
) {
    Column(modifier = Modifier.padding(padding)) {
        when (uiState) {
            is UiState.Success -> {
                ArticleList(uiState.data, onNewsClick)
            }

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowError(
                    text = uiState.message,
                    retryEnable = true
                ) {
                    onRetryClick()
                }
            }
        }
    }
}
