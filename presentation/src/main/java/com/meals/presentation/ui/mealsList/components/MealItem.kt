package com.meals.presentation.ui.mealsList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.meals.presentation.R
import com.meals.presentation.ui.model.MealsUi
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_140
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_5

@Composable
fun SingleMealItem(
    mealsItem: MealsUi,
    onMealItemClick: (String) -> Unit
) {
    Card(
        Modifier
            .padding(UI_SIZE_10.dp)
            .fillMaxWidth()
            .heightIn(max = UI_SIZE_140.dp)
            .clickable { onMealItemClick(mealsItem.mealID) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = UI_SIZE_10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(UI_SIZE_140.dp)) {
                AsyncImage(
                    model = mealsItem.thumbImage,
                    contentDescription = stringResource(R.string.dish_image_cd),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            Text(
                text = mealsItem.meal,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UI_SIZE_5.dp)
            )
        }
    }
}