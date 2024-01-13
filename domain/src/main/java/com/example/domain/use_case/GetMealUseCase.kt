package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.MealDetail
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(idMeal: String): Flow<Response<List<MealDetail>>> {
        return repository.getMealById(idMeal)
    }

}
