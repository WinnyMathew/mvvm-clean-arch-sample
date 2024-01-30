package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.MealDetail
import com.meals.domain.repository.MealRepository
import javax.inject.Inject
class GetMealUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(idMeal: String): Response<List<MealDetail>> {
        return repository.getMealById(idMeal)
    }

}
