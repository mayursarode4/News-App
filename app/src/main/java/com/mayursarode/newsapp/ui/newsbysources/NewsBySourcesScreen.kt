package com.mayursarode.newsapp.ui.newsbysources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.ui.base.ArticleList
import com.mayursarode.newsapp.ui.base.ShowError
import com.mayursarode.newsapp.ui.base.ShowLoading
import com.mayursarode.newsapp.ui.base.UiState

@Composable
fun NewsBySourcesRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NewsBySourcesViewModel = hiltViewModel(),
    sourceId: String? = null,
    countryCode: String? = null,
    languageId: String? = null
) {
    LaunchedEffect(Unit, block = {
        if (!sourceId.isNullOrEmpty()) {
            viewModel.fetchNewsBySources(sourceId)
        }

    })


    val articles by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        NewsBySourcesScreen(articles, onNewsClick)
    }

}

@Composable
fun NewsBySourcesScreen(uiState: UiState<List<ApiArticle>>, onNewsClick: (url: String) -> Unit) {

    when (uiState) {
        is UiState.Success -> {
            ArticleList(articles = uiState.data, onNewsClick = onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message)
        }
    }
}