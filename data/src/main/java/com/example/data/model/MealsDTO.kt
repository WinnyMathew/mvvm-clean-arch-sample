package com.example.data.model

import com.google.gson.annotations.SerializedName


data class MealsDTO(
    @SerializedName("strMeal")
    val mealName: String,
    @SerializedName("strMealThumb")
    val mealThumb: String,
    @SerializedName("idMeal")
    val id: String
)

