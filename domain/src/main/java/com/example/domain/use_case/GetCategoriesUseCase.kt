package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(): Flow<Response<List<Category>>> {
        return repository.getCategories()
    }

}