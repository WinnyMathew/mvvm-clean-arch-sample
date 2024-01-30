package com.meals.data.repository.datasourceImpl

import com.meals.data.api.MealApi
import com.meals.data.mapper.category.CategoryMapper
import com.meals.data.mapper.mealDetails.MealDetailMapper
import com.meals.data.mapper.meals.MealsMapper
import com.meals.data.model.CategoryDTO
import com.meals.data.model.CategoryResponse
import com.meals.data.model.MealDetailDTO
import com.meals.data.model.MealDetailResponse
import com.meals.data.model.MealsDTO
import com.meals.data.model.MealsResponse
import com.meals.data.util.getCategory
import com.meals.data.util.getCategoryDTO
import com.meals.data.util.getMealDetail
import com.meals.data.util.getMealDetailDTO
import com.meals.data.util.getMeals
import com.meals.data.util.getMealsDTO
import com.meals.domain.Response
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceIMPL

    private var apiService: MealApi = mockk()

    private lateinit var category: List<CategoryDTO>
    private lateinit var meals: List<MealsDTO>
    private lateinit var mealDetail: List<MealDetailDTO>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteDataSourceIMPL(
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

        assertEquals(data, Response.Success(getCategory()))
    }

    @Test
    fun `WHEN getCategories invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getCategories() } throws Exception("Error")

        val data = remoteDataSourceImpl.getCategories()

        assertEquals(data, Response.Error("Error"))
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Meals list is RETURNED`() = runTest {
        coEvery { apiService.getMealsByCategory("Beef") } returns MealsResponse(meals)

        val data = remoteDataSourceImpl.getMealsByCategory("Beef")

        assertEquals(data, Response.Success(getMeals()))
    }

    @Test
    fun `WHEN getMealsByCategory invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getMealsByCategory("Beef") } throws Exception("Error")

        val data = remoteDataSourceImpl.getMealsByCategory("Beef")

        assertEquals(data, Response.Error("Error"))
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Meal Detail is RETURNED`() = runTest {
        coEvery { apiService.getMealById("52874") } returns MealDetailResponse(mealDetail)

        val data = remoteDataSourceImpl.getMealById("52874")

        assertEquals(data, Response.Success(listOf(getMealDetail())))
    }

    @Test
    fun `WHEN getMealById invoked THEN Response of Error is RETURNED`() = runTest {
        coEvery { apiService.getMealById("52874") } throws Exception("Error")

        val data = remoteDataSourceImpl.getMealById("52874")

        assertEquals(data, Response.Error("Error"))
    }
}