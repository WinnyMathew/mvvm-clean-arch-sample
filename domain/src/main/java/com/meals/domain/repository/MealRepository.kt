package com.meals.domain.repository

import com.meals.domain.Response
import com.meals.domain.entity.Category
import com.meals.domain.entity.MealDetail
import com.meals.domain.entity.Meals

interface MealRepository {

    suspend fun getCategories(): Response<List<Category>>

    suspend fun getMealsByCategory(strCategory: String): Response<List<Meals>>

    suspend fun getMealById(idMeal: String): Response<List<MealDetail>>

}