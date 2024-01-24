package com.example.domain.usecase

import com.example.domain.Response
import com.example.domain.entity.MealDetail
import com.example.domain.repository.MealRepository
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(idMeal: String): Response<List<MealDetail>> {
        return repository.getMealById(idMeal)
    }

}
