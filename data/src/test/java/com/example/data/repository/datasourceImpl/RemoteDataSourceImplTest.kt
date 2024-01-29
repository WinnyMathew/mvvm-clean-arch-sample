package com.example.data.repository.datasourceImpl

import com.example.data.api.MealApi
import com.example.data.mapper.category.CategoryMapper
import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.mapper.meals.MealsMapper
import com.example.data.model.CategoryDTO
import com.example.data.model.CategoryResponse
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealDetailResponse
import com.example.data.model.MealsDTO
import com.example.data.model.MealsResponse
import com.example.data.util.getCategory
import com.example.data.util.getCategoryDTO
import com.example.data.util.getMealDetail
import com.example.data.util.getMealDetailDTO
import com.example.data.util.getMeals
import com.example.data.util.getMealsDTO
import com.example.domain.Response
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    private var apiService: MealApi = mockk()

    private lateinit var category: List<CategoryDTO>
    private lateinit var meals: List<MealsDTO>
    private lateinit var mealDetail: List<MealDetailDTO>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(
            apiService,
            CategoryMapper(),
            MealsMapper(),
            MealDetailMapper()
        )
        category = getCategoryDTO()
        meals = getMealsDTO()
        mealDetail = getMealDetailDTO()
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Category list is RETURNED`() = runTest {
        coEvery { apiService.getCategories() } returns CategoryResponse(category)

        val data = remoteDataSourceImpl.getCategories()

        Assert.assertEquals(data, Response.Success(getCategory()))
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getCategories() } throws Exception("Error")

        val data = remoteDataSourceImpl.getCategories()

        Assert.assertEquals(data, Response.Error("Error"))
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Meals list is RETURNED`() = runTest {
        coEvery { apiService.getMealsByCategory("Beef") } returns MealsResponse(meals)

        val data = remoteDataSourceImpl.getMealsByCategory("Beef")

        Assert.assertEquals(data, Response.Success(getMeals()))
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getMealsByCategory("Beef") } throws Exception("Error")

        val data = remoteDataSourceImpl.getMealsByCategory("Beef")

        Assert.assertEquals(data, Response.Error("Error"))
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Meal Detail is RETURNED`() = runTest {
        coEvery { apiService.getMealById("52874") } returns MealDetailResponse(mealDetail)

        val data = remoteDataSourceImpl.getMealById("52874")

        Assert.assertEquals(data, Response.Success(listOf(getMealDetail())))
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getMealById("52874") } throws Exception("Error")

        val data = remoteDataSourceImpl.getMealById("52874")

        Assert.assertEquals(data, Response.Error("Error"))
    }
}