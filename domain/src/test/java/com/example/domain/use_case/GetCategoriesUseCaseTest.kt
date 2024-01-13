package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.Category
import com.example.domain.repository.FakeMealRepository
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class GetCategoriesUseCaseTest {
    private lateinit var repository: MealRepository
    lateinit var categoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        repository = FakeMealRepository()
        categoriesUseCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun getCategoryTest() = runBlocking {
        categoriesUseCase().collect {
            when(it) {
                is Response.Success -> {
                    assertTrue(it.data?.size == 2)
                    assertEquals("Beef", it.data?.get(0)?.category)
                }

                else -> {
                    // Do Noting
                }
            }
        }
    }

}