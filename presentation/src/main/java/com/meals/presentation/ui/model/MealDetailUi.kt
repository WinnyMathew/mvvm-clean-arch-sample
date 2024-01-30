package com.meals.presentation.ui.model

data class MealDetailUi(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String
)

fun MealDetailUi.instructions() = this.strInstructions
    .replace("\\r\\n", "\n")
    .replace("\n", "\n\n")
    .trim()