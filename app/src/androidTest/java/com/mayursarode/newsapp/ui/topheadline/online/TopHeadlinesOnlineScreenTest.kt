package com.mayursarode.newsapp.ui.topheadline.online

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.unit.dp
import com.mayursarode.newsapp.R
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.data.model.ApiSource
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.ui.topheadlines.online.TopHeadlinesOnlineScreen
import org.junit.Rule
import org.junit.Test


class TopHeadlinesOnlineScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val paddingValues = PaddingValues(16.dp, 8.dp, 16.dp, 8.dp)

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            TopHeadlinesOnlineScreen(
                padding = paddingValues,
                uiState = UiState.Loading,
                onNewsClick = {},
                onRetryClick = {}
            )

        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getResourceName(R.string.loading))
            .assertExists()


    }

    @Test
    fun articles_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            TopHeadlinesOnlineScreen(
                padding = paddingValues,
                uiState = UiState.Success(testArticles),
                onNewsClick = {},
                onRetryClick = {}
            )
        }

        composeTestRule.onNodeWithText(
            testArticles[0].title,
            substring = true
        )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(
            hasScrollAction()
        )
            .performScrollToNode(
                hasText(
                    testArticles[5].title,
                    substring = true
                )
            )

        composeTestRule.onNodeWithText(
            testArticles[5].title,
            substring = true
        )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenIsUiStateIsError_isShown() {

        val errorMessage = "Error message for you"
        composeTestRule.setContent {
            TopHeadlinesOnlineScreen(
                padding = paddingValues,
                uiState = UiState.Error(errorMessage),
                onNewsClick = {},
                onRetryClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

}

private val testArticles = listOf(
    ApiArticle(
        title = "title1",
        description = "description1",
        url = "url1",
        imageUrl = "imageUrl1",
        source = ApiSource(id = "sourceId1", name = "sourceName1")
    ),
    ApiArticle(
        title = "title2",
        description = "description2",
        url = "url2",
        imageUrl = "imageUrl2",
        source = ApiSource(id = "sourceId2", name = "sourceName2")
    ),
    ApiArticle(
        title = "title3",
        description = "description3",
        url = "url3",
        imageUrl = "imageUrl3",
        source = ApiSource(id = "sourceId3", name = "sourceName3")
    ),
    ApiArticle(
        title = "title4",
        description = "description4",
        url = "url4",
        imageUrl = "imageUrl4",
        source = ApiSource(id = "sourceId4", name = "sourceName4")
    ),
    ApiArticle(
        title = "title5",
        description = "description5",
        url = "url5",
        imageUrl = "imageUrl5",
        source = ApiSource(id = "sourceId5", name = "sourceName5")
    ),
    ApiArticle(
        title = "title6",
        description = "description6",
        url = "url6",
        imageUrl = "imageUrl6",
        source = ApiSource(id = "sourceId6", name = "sourceName6")
    )
)