package com.mayursarode.newsapp.ui.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mayursarode.newsapp.data.model.Language
import com.mayursarode.newsapp.navigation.Screen
import com.mayursarode.newsapp.ui.base.ShowError
import com.mayursarode.newsapp.ui.base.ShowLoading
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.ui.base.topAppBar
import com.mayursarode.newsapp.utils.Constants

@Composable
fun LanguageListRoute(
    navController: NavController,
    viewModel: LanguageListViewModel = hiltViewModel()
) {
    val countries = viewModel.uiState.collectAsStateWithLifecycle()
    val uiState = countries.value

    Scaffold(
        topBar = {
            topAppBar(
                title = Constants.LANGUAGE_SOURCES, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            LanguageListScreen(padding, uiState) { languageId ->
                navController.navigate(route = Screen.NewsBySources.passData(languageId = languageId.id))
            }
        }
    )
}

@Composable
fun LanguageListScreen(
    padding: PaddingValues,
    uiState: UiState<List<Language>>,
    onLanguageClick: (Language) -> Unit
) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        when (uiState) {
            is UiState.Success -> {
                LanguagesList(uiState.data, onLanguageClick)
            }

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowError(text = uiState.message)
            }
        }
    }

}

@Composable
fun LanguagesList(
    languageId: List<Language>,
    onLanguageClick: (Language) -> Unit
) {
    LazyColumn {
        items(languageId.size) { index ->
            LanguagesItem(languageId = languageId[index], onLanguageClick = onLanguageClick)
        }
    }

}

@Composable
fun LanguagesItem(
    languageId: Language,
    onLanguageClick: (Language) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onLanguageClick(languageId) }
            .padding(vertical = 23.dp, horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = languageId.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                fontSize = 18.sp
            )

        }
    }

}