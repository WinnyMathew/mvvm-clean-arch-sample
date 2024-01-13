package com.example.data.repository.datasource

import com.example.data.model.CategoryDTO
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealsDTO

interface RemoteDataSource {
    suspend fun getCategories(): List<CategoryDTO>

    suspend fun getMealsByCategory(strCategory: String): List<MealsDTO>

    suspend fun getMealById(idMeal: String): List<MealDetailDTO>
}
