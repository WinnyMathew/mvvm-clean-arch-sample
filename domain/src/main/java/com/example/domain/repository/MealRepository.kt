package com.example.domain.repository

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getCategories(): Flow<Response<List<Category>>>

    suspend fun getMealsByCategory(strCategory: String): Flow<Response<List<Meals>>>

    suspend fun getMealById(idMeal: String): Flow<Response<List<MealDetail>>>

}