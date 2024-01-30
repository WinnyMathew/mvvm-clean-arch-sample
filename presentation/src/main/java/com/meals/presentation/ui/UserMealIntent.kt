package com.meals.presentation.ui

sealed class UserMealIntent {
    data object GetMealCategories : UserMealIntent()
    data object GetMealsList : UserMealIntent()
    data object GetMealDetail : UserMealIntent()

}