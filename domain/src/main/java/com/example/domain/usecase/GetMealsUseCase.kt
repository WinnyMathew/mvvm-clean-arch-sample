package com.example.domain.usecase

import com.example.domain.Response
import com.example.domain.entity.Meals
import com.example.domain.repository.MealRepository
import javax.inject.Inject
class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(strCategory: String): Response<List<Meals>> {
        return repository.getMealsByCategory(strCategory)
    }
}

