package com.example.data.api

import com.example.data.model.CategoryDTO
import com.example.data.model.CategoryResponse
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealDetailResponse
import com.example.data.model.MealsDTO
import com.example.data.model.MealsResponse
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
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