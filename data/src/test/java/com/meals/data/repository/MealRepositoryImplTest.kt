package com.meals.data.repository

import com.meals.data.util.getCategory
import com.meals.data.util.getMealDetail
import com.meals.data.util.getMeals
import com.meals.domain.Response
import com.meals.data.repository.datasource.RemoteDataSource
import com.meals.domain.entity.Category
import com.meals.domain.entity.MealDetail
import com.meals.domain.entity.Meals
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MealRepositoryImplTest {

    private lateinit var repositoryImpl: MealRepositoryImpl
    private var remoteDataSource: RemoteDataSource = mockk()

    private lateinit var category: List<Category>
    private lateinit var meals: List<Meals>
    private lateinit var mealDetail: List<MealDetail>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = MealRepositoryImpl(remoteDataSource)
        category = getCategory()
        meals = getMeals()
        mealDetail = listOf(getMealDetail())
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Category list is RETURNED`() = runTest {
        coEvery { remoteDataSource.getCategories() } returns Response.Success(category)

        val response = repositoryImpl.getCategories()
        if (response is Response.Success) {
            assertTrue(response.data == category)
        }
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { remoteDataSource.getCategories() } returns Response.Error("Error")

        val response = repositoryImpl.getCategories()
        if (response is Response.Error) {
            assertTrue(response.message == "Error")
        }
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Meals list is RETURNED`() = runTest {
        coEvery { remoteDataSource.getMealsByCategory("Beef") } returns Response.Success(meals)

        val response = repositoryImpl.getMealsByCategory("Beef")
        if (response is Response.Success) {
            assertTrue(response.data == meals)
        }
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { remoteDataSource.getMealsByCategory("Beef") } returns Response.Error("Error")

        val response = repositoryImpl.getMealsByCategory("Beef")
        if (response is Response.Error) {
            assertTrue(response.message == "Error")
        }
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Meal Detail is RETURNED`() = runTest {
        coEvery { remoteDataSource.getMealById("52874") } returns Response.Success(mealDetail)

        val response = repositoryImpl.getMealById("52874")
        if (response is Response.Success) {
            assertTrue(response.data == mealDetail)
        }
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { remoteDataSource.getMealById("52874") } returns Response.Error("Error")

        val response = repositoryImpl.getMealById("52874")
        if (response is Response.Error) {
            assertTrue(response.message == "Error")
        }
    }
}