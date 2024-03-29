package com.meals.presentation.ui.mealDetail.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.meals.presentation.R
import com.meals.presentation.ui.model.MealDetailUi
import com.meals.presentation.ui.model.instructions
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_1
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_180
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_5

@Composable
fun MealDetailItem(
    mealInfo: MealDetailUi
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(UI_SIZE_180.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            AsyncImage(
                model = mealInfo.strMealThumb,
                contentDescription = stringResource(R.string.dish_image_cd),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
            )
        }
        Text(
            text = mealInfo.strMeal,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = UI_SIZE_5.dp)
        )
        Text(
            text = mealInfo.strCategory,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = UI_SIZE_10.dp)
        )
        Text(
            text = mealInfo.strArea,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = UI_SIZE_5.dp)
        )
        Divider(
            thickness = UI_SIZE_1.dp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = UI_SIZE_5.dp, horizontal = UI_SIZE_10.dp)
        )
    }
}

@Composable
fun MealInstructions(
    mealInfo: MealDetailUi
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UI_SIZE_10.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = mealInfo.instructions(),
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UI_SIZE_5.dp)
        )
    }
}