package com.meals.presentation.ui.categoryList

sealed class CategoryScreenIntent {
    data object GetMealCategories : CategoryScreenIntent()
    data class CategoryItemClick(val strCategory: String) : CategoryScreenIntent()
}
