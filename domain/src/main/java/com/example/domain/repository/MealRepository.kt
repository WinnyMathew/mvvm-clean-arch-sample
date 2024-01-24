package com.example.domain.repository

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getCategories(): Response<List<Category>>

    suspend fun getMealsByCategory(strCategory: String): Response<List<Meals>>

    suspend fun getMealById(idMeal: String): Response<List<MealDetail>>

}