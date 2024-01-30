package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.Category
import com.meals.domain.repository.MealRepository
import com.meals.domain.util.getCategory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {
    private var repository: MealRepository = mockk()
    lateinit var categoriesUseCase: GetCategoriesUseCase

    private lateinit var category: List<Category>

    @Before
    fun setUp() {
        categoriesUseCase = GetCategoriesUseCase(repository)
        category = getCategory()
    }

    @Test
    fun `WHEN getCategory invoked THEN Category list is RETURNED`() = runBlocking {
        coEvery { repository.getCategories() } returns Response.Success(category)

        val response = categoriesUseCase()
        if (response is Response.Success) {
            assertTrue(response.data?.size == 2)
            assertEquals("Beef", response.data?.get(0)?.category)
        }
    }

    @Test
    fun `WHEN getCategory invoked THEN Error is RETURNED`() = runBlocking {
        coEvery { repository.getCategories() } returns Response.Error("Error")

        val response = categoriesUseCase()
        if (response is Response.Error) {
            assertEquals("Error", response.message)
        }
    }

}