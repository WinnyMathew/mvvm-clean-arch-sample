package com.meals.presentation.ui.categoryList

sealed class CategoryScreenIntent {
    data object GetMealCategories : CategoryScreenIntent()
}
