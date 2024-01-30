package com.meals.presentation.ui.mealsList

import com.meals.presentation.ui.model.MealsUi

data class MealsListState(
    val isLoading: Boolean = false,
    val meals: List<MealsUi> = emptyList(),
    val error: String = String()
)
