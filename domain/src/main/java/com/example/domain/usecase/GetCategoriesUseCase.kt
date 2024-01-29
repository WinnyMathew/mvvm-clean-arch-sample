package com.example.domain.usecase

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.repository.MealRepository
import javax.inject.Inject
class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(): Response<List<Category>> {
        return repository.getCategories()
    }

}