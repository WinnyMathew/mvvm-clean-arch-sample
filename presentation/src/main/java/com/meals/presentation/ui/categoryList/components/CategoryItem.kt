package com.meals.presentation.ui.categoryList.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.meals.presentation.R
import com.meals.presentation.ui.model.CategoryUi
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_1
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_10
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_25
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_5
import com.meals.presentation.ui.theme.AppDimens.UI_SIZE_75

@Composable
fun SingleCategoryItem(
    categoryItem: CategoryUi,
    onCategoryItemClick: (String) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(UI_SIZE_10.dp)
            .clickable { onCategoryItemClick(categoryItem.category) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = UI_SIZE_10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(UI_SIZE_5.dp))
        ) {
            AsyncImage(
                model = categoryItem.thumbImage,
                contentDescription = stringResource(R.string.category_image_cd),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(CenterVertically)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(
                modifier = Modifier
                    .width(UI_SIZE_25.dp)
            )
            Divider(
                color = MaterialTheme.colorScheme.primary.copy(0.4f),
                modifier = Modifier
                    .height(UI_SIZE_75.dp)
                    .width(UI_SIZE_1.dp)
                    .align(CenterVertically)
            )
            Text(
                text = categoryItem.category,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically)
            )
        }
    }
}