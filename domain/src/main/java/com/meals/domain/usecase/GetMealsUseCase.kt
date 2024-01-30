package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.Meals
import com.meals.domain.repository.MealRepository
import javax.inject.Inject
class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(strCategory: String): Response<List<Meals>> {
        return repository.getMealsByCategory(strCategory)
    }
}

