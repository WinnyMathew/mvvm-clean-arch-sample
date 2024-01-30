package com.meals.presentation.ui.mealDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.meals.presentation.ui.HeadingTextComponent
import com.meals.presentation.ui.TextTitleMealInfo
import com.meals.presentation.ui.mealDetail.components.MealDetailItem
import com.meals.presentation.ui.mealDetail.components.MealInstructions
import com.meals.presentation.R
import com.meals.presentation.ui.UserMealIntent
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_15
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_30
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_5

@Composable
fun MealDetailScreen(
    navController: NavController,
    viewModel: MealDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
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
                    text = stringResource(R.string.meal_info_title),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(UI_SIZE_10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = UI_SIZE_10.dp
                )
            ) {
                state.meals.firstOrNull()?.let { meal ->
                    MealDetailItem(mealInfo = meal)
                }
                Spacer(modifier = Modifier.height(UI_SIZE_10.dp))

                TextTitleMealInfo(stringResource(R.string.instructions_header))
                state.meals.firstOrNull()?.let { meal ->
                    MealInstructions(mealInfo = meal)
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
                    .padding(horizontal = UI_SIZE_15.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }

}