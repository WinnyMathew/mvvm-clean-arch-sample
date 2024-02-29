package com.meals.presentation.ui.mealsList

sealed class MealListScreenIntent {
    data object GetMealsList : MealListScreenIntent()
}