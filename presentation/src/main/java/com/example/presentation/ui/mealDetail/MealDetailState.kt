package com.example.presentation.ui.mealDetail

import com.example.domain.entity.MealDetail
data class MealDetailState(
    val isLoading: Boolean = false,
    val meals: List<MealDetail> = emptyList(),
    val error: String = String()
)