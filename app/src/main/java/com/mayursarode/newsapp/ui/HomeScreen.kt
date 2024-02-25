package com.mayursarode.newsapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mayursarode.newsapp.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            text = "TOP HEADLINES ONLINE",
            onClickAction = {
                navController.navigate(route = Screen.TopHeadlinesOnline.route)
            })

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = "TOP HEADLINES OFFLINE",
            onClickAction = {
                navController.navigate(route = Screen.TopHeadlinesOffline.route)
            })
    }
}

@Composable
fun CustomButton(
    text: String,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    buttonWidth: Dp = 350.dp,
    buttonHeight: Dp = 50.dp,
    fontSize: TextUnit = 25.sp
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Button(
            onClick = { onClickAction.invoke() },
            modifier = modifier
                .width(buttonWidth)
                .height(buttonHeight),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                style = TextStyle(fontSize = fontSize),
                text = text,
                minLines = 2
            )

        }
    }
}