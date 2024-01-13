package com.example.data.repository

import com.example.data.mapper.category.CategoryMapper
import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.mapper.meals.MealsMapper
import com.example.data.repository.datasource.RemoteDataSource
import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val categoryMapper: CategoryMapper,
    private val mealsMapper: MealsMapper,
    private val mealDetailMapper: MealDetailMapper
) : MealRepository {
    override suspend fun getCategories() = repoFlow {
        categoryMapper.map(remoteDataSource.getCategories())
    }

    override suspend fun getMealsByCategory(strCategory: String) = repoFlow {
        mealsMapper.map(remoteDataSource.getMealsByCategory(strCategory))
    }

    override suspend fun getMealById(idMeal: String) = repoFlow {
        mealDetailMapper.map(remoteDataSource.getMealById(idMeal))
    }
}

inline fun <T> repoFlow(
    crossinline block: suspend () -> T,
): Flow<Response<T>> = flow {
    try {
        emit(Response.Loading)
        val repoResult = block()
        emit(Response.Success(repoResult))
    } catch (e: HttpException) {
        emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
    } catch (e: IOException) {
        emit(Response.Error("Couldn't reach server. Check your internet connection"))
    } catch (e: Exception) {
        emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }
}