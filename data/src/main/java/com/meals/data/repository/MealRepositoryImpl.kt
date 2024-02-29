package com.meals.data.repository

import com.meals.data.repository.datasource.RemoteDataSource
import com.meals.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MealRepository {
    override suspend fun getCategories() = remoteDataSource.getCategories()
    override suspend fun getMealsByCategory(strCategory: String) =
        remoteDataSource.getMealsByCategory(strCategory)
    override suspend fun getMealById(idMeal: String) = remoteDataSource.getMealById(idMeal)
}