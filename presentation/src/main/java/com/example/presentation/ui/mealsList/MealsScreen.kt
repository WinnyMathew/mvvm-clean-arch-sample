package com.example.presentation.ui.mealsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.HeadingTextComponent
import com.example.presentation.R
import com.example.presentation.ui.UserMealIntent
import com.example.presentation.ui.mealsList.components.SingleMealItem
import com.example.presentation.ui.theme.AppDimens
import com.example.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.example.presentation.ui.theme.AppDimens.UI_SIZE_30
import com.example.presentation.ui.theme.AppDimens.UI_SIZE_5

@Composable
fun MealsScreen(
    onMealItemClick: (String) -> Unit,
    navController: NavController,
    viewModel: MealsListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.userIntent.send(UserMealIntent.GetMealsList)
    }

    Box(Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(UI_SIZE_10.dp),
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = stringResource(R.string.back_icon_cd),
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(start = UI_SIZE_5.dp, end = UI_SIZE_10.dp, top = UI_SIZE_10.dp)
                        .clip(CircleShape)
                        .size(UI_SIZE_30.dp)
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                        .alignByBaseline()
                )
                HeadingTextComponent(
                    text = stringResource(R.string.meals_title),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(UI_SIZE_10.dp)
            ) {
                items(state.meals) { dishes ->
                    SingleMealItem(
                        mealsItem = dishes,
                        onMealItemClick = onMealItemClick
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }

}