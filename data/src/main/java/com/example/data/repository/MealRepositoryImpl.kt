package com.example.data.repository

import com.example.data.mapper.category.CategoryMapper
import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.mapper.meals.MealsMapper
import com.example.domain.datasource.RemoteDataSource
import com.example.domain.Response
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MealRepository {
    override suspend fun getCategories() = remoteDataSource.getCategories()
    override suspend fun getMealsByCategory(strCategory: String) =
        remoteDataSource.getMealsByCategory(strCategory)
    override suspend fun getMealById(idMeal: String) = remoteDataSource.getMealById(idMeal)
}