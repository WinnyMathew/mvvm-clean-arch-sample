package com.meals.data.model

import com.google.gson.annotations.SerializedName


data class MealDetailDto(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strArea")
    val strArea: String,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)
