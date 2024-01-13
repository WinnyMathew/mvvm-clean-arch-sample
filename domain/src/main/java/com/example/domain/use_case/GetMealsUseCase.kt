package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.Meals
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(strCategory: String): Flow<Response<List<Meals>>> {
        return repository.getMealsByCategory(strCategory)
    }
}

