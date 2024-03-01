package com.meals.presentation.ui.mealsList

sealed class MealListScreenIntent {
    data object GetMealsList : MealListScreenIntent()
    data class MealsListItemClick(val idMeal: String) : MealListScreenIntent()
}