package com.meals.data.repository.datasourceImpl

import com.meals.data.api.MealApi
import com.meals.data.mapper.category.CategoryMapper
import com.meals.data.mapper.mealDetails.MealDetailMapper
import com.meals.data.mapper.meals.MealsMapper
import com.meals.domain.Response
import com.meals.domain.datasource.RemoteDataSource
import com.meals.domain.entity.Category
import com.meals.domain.entity.MealDetail
import com.meals.domain.entity.Meals
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
