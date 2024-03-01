package com.meals.presentation.ui.categoryList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.meals.presentation.ui.HeadingTextComponent
import com.meals.presentation.R
import com.meals.presentation.ui.SideEffect
import com.meals.presentation.navigation.Screen
import com.meals.presentation.ui.categoryList.components.SingleCategoryItem
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_15

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
            viewModel.sideEffect.collect {
                when (it) {
                    is SideEffect.OnItemClickNavigateToNextScreen -> {
                        navController.navigate("${Screen.MealsScreen.route}/${it.value}")
                    }
                }
            }
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(UI_SIZE_10.dp),
                verticalAlignment = CenterVertically,
            ) {
                HeadingTextComponent(
                    text = stringResource(R.string.categories_title),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = UI_SIZE_10.dp)
                        .weight(1f)
                )

            }
            LazyColumn(
                contentPadding = PaddingValues(UI_SIZE_10.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                items(state.categories) { category ->
                    SingleCategoryItem(
                        categoryItem = category,
                        onCategoryItemClick = { strCategory ->
                            viewModel.handleIntent(
                                CategoryScreenIntent.CategoryItemClick(strCategory)
                            )
                        }
                    )
                }
            }
        }
        state.error?.let {
            Text(
                text = it,
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



