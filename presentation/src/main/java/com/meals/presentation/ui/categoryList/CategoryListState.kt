package com.meals.presentation.ui.categoryList

import com.meals.domain.entity.Category
data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = String()
)
