package com.meals.presentation.ui.mealDetail

import com.meals.domain.entity.MealDetail
data class MealDetailState(
    val isLoading: Boolean = false,
    val meals: List<MealDetail> = emptyList(),
    val error: String = String()
)