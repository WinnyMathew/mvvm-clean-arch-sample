package com.example.domain.usecase

import com.example.domain.Response
import com.example.domain.repository.FakeMealRepository
import com.example.domain.repository.MealRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {
    private lateinit var repository: MealRepository
    lateinit var categoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        repository = FakeMealRepository()
        categoriesUseCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun `WHEN getCategory invoked THEN Category list is RETURNED`() = runBlocking {
        when (val response = categoriesUseCase()) {
            is Response.Success -> {
                assertTrue(response.data?.size == 2)
                assertEquals("Beef", response.data?.get(0)?.category)
            }

            else -> {
                // Do Noting
            }
        }
    }

    @Test
    fun `WHEN getCategory invoked THEN Error is RETURNED`() = runBlocking {
        repository = mockk()
        coEvery { repository.getCategories() } returns Response.Error("Error")
        when (val response = categoriesUseCase()) {
            is Response.Error -> {
                assertEquals("Error", response.message)
            }

            else -> {
                // Do Noting
            }
        }
    }

}