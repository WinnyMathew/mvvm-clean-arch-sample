package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.MealDetail
import com.example.domain.repository.FakeMealRepository
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

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
    fun getMealsListTest() = runBlocking {
        mealUseCaseTest(MEAL_ID).collect {
            when (it) {
                is Response.Success -> {
                    Assert.assertEquals("Beef and Mustard Pie", it.data?.get(0)?.strMeal)
                    Assert.assertEquals("Beef", it.data?.get(0)?.strCategory)
                    Assert.assertEquals("British", it.data?.get(0)?.strArea)
                }

                else -> {
                    // Do Noting
                }
            }
        }
    }

}
