package com.example.data.repository.datasourceimpl

import com.example.data.api.MealApi
import com.example.data.repository.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MealApi
) : RemoteDataSource {

    override suspend fun getCategories() = apiService.getCategories().categories

    override suspend fun getMealsByCategory(strCategory: String) =
        apiService.getMealsByCategory(strCategory).meals

    override suspend fun getMealById(idMeal: String) = apiService.getMealById(idMeal).meals
}
