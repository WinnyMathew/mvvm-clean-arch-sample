package com.meals.presentation.ui.mealsList

import com.meals.domain.entity.Meals

data class MealsListState(
    val isLoading: Boolean = false,
    val meals: List<Meals> = emptyList(),
    val error: String = String()
)
