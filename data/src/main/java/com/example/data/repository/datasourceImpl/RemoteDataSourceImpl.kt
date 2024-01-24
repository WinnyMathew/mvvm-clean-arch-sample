package com.example.data.repository.datasourceImpl

import com.example.data.api.MealApi
import com.example.data.mapper.category.CategoryMapper
import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.mapper.meals.MealsMapper
import com.example.domain.Response
import com.example.domain.datasource.RemoteDataSource
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MealApi,
    private val categoryMapper: CategoryMapper,
    private val mealsMapper: MealsMapper,
    private val mealDetailMapper: MealDetailMapper
) : RemoteDataSource {

    override suspend fun getCategories(): Response<List<Category>> {
        return try {
            val data = categoryMapper.map(apiService.getCategories().categories)
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    override suspend fun getMealsByCategory(strCategory: String): Response<List<Meals>> {
        return try {
            val data = mealsMapper.map(apiService.getMealsByCategory(strCategory).meals)
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    override suspend fun getMealById(idMeal: String): Response<List<MealDetail>> {
        return try {
            val data = mealDetailMapper.map(apiService.getMealById(idMeal).meals)
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}
