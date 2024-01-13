package com.example.domain.use_case

import com.example.domain.Response
import com.example.domain.entity.Meals
import com.example.domain.repository.FakeMealRepository
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

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
    fun getMealsListTest() = runBlocking {
        mealsUseCaseTest(CATEGORY_STRING).collect {
            when (it) {
                is Response.Success -> {
                    Assert.assertTrue(it.data?.size == 2)
                    Assert.assertEquals("Beef and Mustard Pie", it.data?.get(0)?.meal)
                }

                else -> {
                    // Do Noting
                }
            }
        }
    }
}

