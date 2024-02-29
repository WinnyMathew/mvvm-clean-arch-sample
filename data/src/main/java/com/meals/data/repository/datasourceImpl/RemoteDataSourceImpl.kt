package com.meals.data.repository.datasourceImpl

import com.meals.data.api.MealApi
import com.meals.data.mapper.category.CategoryMapper
import com.meals.data.mapper.mealDetails.MealDetailMapper
import com.meals.data.mapper.meals.MealsMapper
import com.meals.domain.Response
import com.meals.data.repository.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MealApi,
    private val categoryMapper: CategoryMapper,
    private val mealsMapper: MealsMapper,
    private val mealDetailMapper: MealDetailMapper
) : RemoteDataSource {

    override suspend fun getCategories() = try {
        val data = categoryMapper.map(apiService.getCategories().categories)
        Response.Success(data)
    } catch (e: Exception) {
        Response.Error(e.localizedMessage ?: "An unexpected error occurred")
    }

    override suspend fun getMealsByCategory(strCategory: String) = try {
        val data = mealsMapper.map(apiService.getMealsByCategory(strCategory).meals)
        Response.Success(data)
    } catch (e: Exception) {
        Response.Error(e.localizedMessage ?: "An unexpected error occurred")
    }

    override suspend fun getMealById(idMeal: String) = try {
        val data = mealDetailMapper.map(apiService.getMealById(idMeal).meals)
        Response.Success(data)
    } catch (e: Exception) {
        Response.Error(e.localizedMessage ?: "An unexpected error occurred")
    }
}
