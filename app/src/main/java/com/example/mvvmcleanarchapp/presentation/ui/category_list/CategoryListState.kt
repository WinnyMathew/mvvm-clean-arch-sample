package com.example.mvvmcleanarchapp.presentation.ui.category_list

import com.example.domain.entity.Category


data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)
