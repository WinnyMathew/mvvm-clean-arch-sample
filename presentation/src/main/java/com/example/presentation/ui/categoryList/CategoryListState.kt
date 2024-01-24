package com.example.presentation.ui.categoryList

import com.example.domain.entity.Category
data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = String()
)
