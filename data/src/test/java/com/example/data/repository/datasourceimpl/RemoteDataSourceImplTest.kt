package com.example.data.repository.datasourceimpl

import com.example.data.api.MealApi
import com.example.data.model.CategoryDTO
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealsDTO
import com.example.data.util.getCategoryDTO
import com.example.data.util.getMealDetailDTO
import com.example.data.util.getMealsDTO
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @RelaxedMockK
    private lateinit var apiService: MealApi

    private lateinit var category: List<CategoryDTO>
    private lateinit var meals: List<MealsDTO>
    private lateinit var mealDetail: List<MealDetailDTO>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
        category = getCategoryDTO()
        meals = getMealsDTO()
        mealDetail = getMealDetailDTO()
    }

    @Test
    fun getCategoryTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getCategories() } returns category

            val data = remoteDataSourceImpl.getCategories()

            Assert.assertEquals(data, category)
        }
    }

    @Test
    fun getMealsListTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getMealsByCategory("Beef") } returns meals

            val data = remoteDataSourceImpl.getMealsByCategory("Beef")

            Assert.assertEquals(data, meals)
        }
    }

    @Test
    fun getMealsDetailTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getMealById("52874") } returns mealDetail

            val data = remoteDataSourceImpl.getMealById("52874")

            Assert.assertEquals(data, mealDetail)
        }
    }
}