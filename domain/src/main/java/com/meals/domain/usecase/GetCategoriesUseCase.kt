package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.Category
import com.meals.domain.repository.MealRepository
import javax.inject.Inject
class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(): Response<List<Category>> {
        return repository.getCategories()
    }

}