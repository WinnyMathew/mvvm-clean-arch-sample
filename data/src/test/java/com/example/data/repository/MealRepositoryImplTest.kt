package com.example.data.repository

import com.example.data.util.getCategory
import com.example.data.util.getMealDetail
import com.example.data.util.getMeals
import com.example.domain.Response
import com.example.domain.datasource.RemoteDataSource
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
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
    fun `WHEN getCategories invoked THEN Response of Category list is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getCategories() } returns Response.Success(category)

            when(val response = repositoryImpl.getCategories()) {
                is Response.Success -> {
                    Truth.assertThat(response.data).isEqualTo(category)
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Error is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getCategories() } returns Response.Error("Error")

            when(val response = repositoryImpl.getCategories()) {
                is Response.Error -> {
                    Truth.assertThat(response.message).isEqualTo("Error")
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Meals list is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getMealsByCategory("Beef") } returns Response.Success(meals)

            when(val response = repositoryImpl.getMealsByCategory("Beef")) {
                is Response.Success -> {
                    Truth.assertThat(response.data).isEqualTo(meals)
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Error is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getMealsByCategory("Beef") } returns Response.Error("Error")


            when(val response = repositoryImpl.getMealsByCategory("Beef")) {
                is Response.Error -> {
                    Truth.assertThat(response.message).isEqualTo("Error")
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Meal Detail is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getMealById("52874") } returns Response.Success(mealDetail)

            when(val response = repositoryImpl.getMealById("52874")) {
                is Response.Success -> {
                    Truth.assertThat(response.data).isEqualTo(mealDetail)
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Error is RETURNED`() {
        runTest {
            coEvery { remoteDataSource.getMealById("52874") } returns Response.Error("Error")


            when(val response = repositoryImpl.getMealById("52874")) {
                is Response.Error -> {
                    Truth.assertThat(response.message).isEqualTo("Error")
                }
                else -> {
                    // Do Noting
                }
            }
        }
    }
}