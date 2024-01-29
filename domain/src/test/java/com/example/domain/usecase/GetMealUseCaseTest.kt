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

private const val MEAL_ID = "52874"

class GetMealUseCaseTest {

    private lateinit var repository: MealRepository
    lateinit var mealUseCaseTest: GetMealUseCase

    @Before
    fun setUp() {
        repository = FakeMealRepository()
        mealUseCaseTest = GetMealUseCase(repository)
    }

    @Test
    fun `WHEN getMealDetail invoked THEN MealDetail is RETURNED`() = runBlocking {
        when (val response = mealUseCaseTest(MEAL_ID)) {
            is Response.Success -> {
                Assert.assertEquals("Beef and Mustard Pie", response.data?.get(0)?.strMeal)
                Assert.assertEquals("Beef", response.data?.get(0)?.strCategory)
                Assert.assertEquals("British", response.data?.get(0)?.strArea)
            }

            else -> {
                // Do Noting
            }
        }
    }

    @Test
    fun `WHEN getMealDetail invoked THEN Error is RETURNED`() = runBlocking {
        repository = mockk()
        coEvery { repository.getMealById(MEAL_ID) } returns Response.Error("Error")
        when (val response = mealUseCaseTest(MEAL_ID)) {
            is Response.Error -> {
                Assert.assertEquals("Error", response.message)
            }

            else -> {
                // Do Noting
            }
        }
    }

}
