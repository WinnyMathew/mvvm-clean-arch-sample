package com.meals.presentation.navigation

sealed class Screen(val route: String) {
    data object CategoriesScreen: Screen("categories_screen")
    data object MealsScreen: Screen("meals_screen")
    data object MealDetailScreen: Screen("meal_detail_screen")
}
