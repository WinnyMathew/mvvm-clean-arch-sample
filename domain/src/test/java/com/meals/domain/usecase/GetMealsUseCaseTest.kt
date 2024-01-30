package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.Meals
import com.meals.domain.repository.MealRepository
import com.meals.domain.util.getMeals
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

private const val CATEGORY_STRING = "Beef"

class GetMealsUseCaseTest {
    private var repository: MealRepository = mockk()
    lateinit var mealsUseCaseTest: GetMealsUseCase

    private lateinit var meals: List<Meals>

    @Before
    fun setUp() {
        mealsUseCaseTest = GetMealsUseCase(repository)
        meals = getMeals()
    }

    @Test
    fun `WHEN getMealsList invoked THEN Meals list is RETURNED`() = runBlocking {
        coEvery { repository.getMealsByCategory(CATEGORY_STRING) } returns Response.Success(meals)

        val response = mealsUseCaseTest(CATEGORY_STRING)
        if (response is Response.Success) {
            assertTrue(response.data?.size == 2)
            assertEquals("Beef and Mustard Pie", response.data?.get(0)?.meal)
        }
    }

    @Test
    fun `WHEN getMealsList invoked THEN Error is RETURNED`() = runBlocking {
        coEvery { repository.getMealsByCategory(CATEGORY_STRING) } returns Response.Error("Error")

        val response = mealsUseCaseTest(CATEGORY_STRING)
        if (response is Response.Error) {
            assertEquals("Error", response.message)
        }
    }
}

