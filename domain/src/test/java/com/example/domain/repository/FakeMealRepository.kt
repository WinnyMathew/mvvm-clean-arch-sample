package com.example.domain.repository

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import com.example.domain.util.getCategory
import com.example.domain.util.getMealDetail
import com.example.domain.util.getMeals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMealRepository: MealRepository {
    override suspend fun getCategories() = flow {
        emit(Response.Success(getCategory()))
    }

    override suspend fun getMealsByCategory(strCategory: String) = flow {
        emit(Response.Success(getMeals()))
    }

    override suspend fun getMealById(idMeal: String) = flow {
        emit(Response.Success(listOf(getMealDetail())))
    }

}