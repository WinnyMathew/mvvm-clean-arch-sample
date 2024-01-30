package com.meals.presentation.ui.mealDetail

import com.meals.presentation.ui.model.MealDetailUi

data class MealDetailState(
    val isLoading: Boolean = false,
    val meals: List<MealDetailUi> = emptyList(),
    val error: String = String()
)