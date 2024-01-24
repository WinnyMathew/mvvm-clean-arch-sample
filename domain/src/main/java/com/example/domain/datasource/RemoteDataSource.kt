package com.example.domain.datasource

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals

interface RemoteDataSource {
    suspend fun getCategories(): Response<List<Category>>

    suspend fun getMealsByCategory(strCategory: String): Response<List<Meals>>

    suspend fun getMealById(idMeal: String): Response<List<MealDetail>>
}
