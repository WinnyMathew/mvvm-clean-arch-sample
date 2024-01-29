package com.example.domain.entity
data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String
)
 fun MealDetail.instructions() = this.strInstructions
    .replace("\\r\\n", "\n")
    .replace("\n", "\n\n")
    .trim()
