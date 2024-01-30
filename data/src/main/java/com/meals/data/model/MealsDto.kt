package com.meals.data.model

import com.google.gson.annotations.SerializedName


data class MealsDto(
    @SerializedName("strMeal")
    val mealName: String,
    @SerializedName("strMealThumb")
    val mealThumb: String,
    @SerializedName("idMeal")
    val id: String
)

