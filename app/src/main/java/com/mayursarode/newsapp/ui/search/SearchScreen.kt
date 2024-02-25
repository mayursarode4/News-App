package com.mayursarode.newsapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.ui.base.topAppBar
import com.mayursarode.newsapp.ui.newsbysources.NewsBySourcesScreen
import com.mayursarode.newsapp.utils.Constants


@Composable
fun SearchScreenRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val searchResult = viewModel.uiState.collectAsStateWithLifecycle()
    val uiState = searchResult.value

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.SEARCH_NEWS, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                SearchContent(uiState, onNewsClick, viewModel)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    uiState: UiState<List<ApiArticle>>,
    onNewsClick: (url: String) -> Unit,
    viewModel: SearchViewModel
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(query = text, onQueryChange = {
        text = it
        viewModel.searchNews(it)
    }, onSearch = { active = false }, active = active, placeholder = {
        Text(text = "Search News")
    }, leadingIcon = {
        Icon(
            imageVector = Icons.Default.Search,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }, content = {
        NewsBySourcesScreen(
            uiState = uiState,
            onNewsClick = onNewsClick
        )
    }, onActiveChange = {
        active = it
    }, tonalElevation = 0.dp
    )

}
