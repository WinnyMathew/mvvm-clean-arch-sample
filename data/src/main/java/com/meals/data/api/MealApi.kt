package com.meals.data.api

import com.meals.data.model.CategoryResponse
import com.meals.data.model.MealDetailResponse
import com.meals.data.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") strCategory: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") idMeal: String): MealDetailResponse
}