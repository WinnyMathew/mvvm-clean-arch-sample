package com.meals.presentation.ui.mealDetail

sealed class MealDetailScreenIntent {
    data object GetMealDetail : MealDetailScreenIntent()
}