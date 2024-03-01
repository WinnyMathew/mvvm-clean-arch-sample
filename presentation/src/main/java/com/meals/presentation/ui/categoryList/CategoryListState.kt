package com.meals.presentation.ui.categoryList

import com.meals.presentation.ui.model.CategoryUi

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<CategoryUi> = emptyList(),
    val error: String? = null
)
