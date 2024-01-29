package com.example.domain.usecase

import com.example.domain.Response
import com.example.domain.repository.FakeMealRepository
import com.example.domain.repository.MealRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private const val CATEGORY_STRING = "Beef"

class GetMealsUseCaseTest {
    private lateinit var repository: MealRepository
    lateinit var mealsUseCaseTest: GetMealsUseCase

    @Before
    fun setUp() {
        repository = FakeMealRepository()
        mealsUseCaseTest = GetMealsUseCase(repository)
    }

    @Test
    fun `WHEN getMealsList invoked THEN Meals list is RETURNED`() = runBlocking {
        when (val response = mealsUseCaseTest(CATEGORY_STRING)) {
            is Response.Success -> {
                Assert.assertTrue(response.data?.size == 2)
                Assert.assertEquals("Beef and Mustard Pie", response.data?.get(0)?.meal)
            }

            else -> {
                // Do Noting
            }
        }
    }

    @Test
    fun `WHEN getMealsList invoked THEN Error is RETURNED`() = runBlocking {
        repository = mockk()
        coEvery { repository.getMealsByCategory(CATEGORY_STRING) } returns Response.Error("Error")
        when (val response = mealsUseCaseTest(CATEGORY_STRING)) {
            is Response.Error -> {
                Assert.assertEquals("Error", response.message)
            }

            else -> {
                // Do Noting
            }
        }
    }
}

