package com.meals.domain.usecase

import com.meals.domain.Response
import com.meals.domain.entity.MealDetail
import com.meals.domain.repository.MealRepository
import com.meals.domain.util.getMealDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val MEAL_ID = "52874"

class GetMealUseCaseTest {
    private var repository: MealRepository = mockk()
    lateinit var mealUseCaseTest: GetMealUseCase

    private lateinit var mealDetail: List<MealDetail>

    @Before
    fun setUp() {
        mealUseCaseTest = GetMealUseCase(repository)
        mealDetail = listOf(getMealDetail())
    }

    @Test
    fun `WHEN getMealDetail invoked THEN MealDetail is RETURNED`() = runBlocking {
        coEvery { repository.getMealById(MEAL_ID) } returns Response.Success(mealDetail)

        val response = mealUseCaseTest(MEAL_ID)
        if (response is Response.Success) {
            assertEquals("Beef and Mustard Pie", response.data?.get(0)?.strMeal)
            assertEquals("Beef", response.data?.get(0)?.strCategory)
            assertEquals("British", response.data?.get(0)?.strArea)
        }
    }

    @Test
    fun `WHEN getMealDetail invoked THEN Error is RETURNED`() = runBlocking {
        coEvery { repository.getMealById(MEAL_ID) } returns Response.Error("Error")

        val response = mealUseCaseTest(MEAL_ID)
        if (response is Response.Error) {
            assertEquals("Error", response.message)
        }
    }

}
