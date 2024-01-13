package com.example.data.repository

import com.example.data.api.MealApi
import com.example.data.mapper.category.CategoryMapper
import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.mapper.meals.MealsMapper
import com.example.data.model.CategoryDTO
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealsDTO
import com.example.data.repository.datasourceimpl.RemoteDataSourceImpl
import com.example.data.util.getCategory
import com.example.data.util.getCategoryDTO
import com.example.data.util.getMealDetail
import com.example.data.util.getMealDetailDTO
import com.example.data.util.getMeals
import com.example.data.util.getMealsDTO
import com.example.domain.Response
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MealRepositoryImplTest {

    private lateinit var repositoryImpl: MealRepositoryImpl
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @RelaxedMockK
    private lateinit var apiService: MealApi
    @RelaxedMockK
    private lateinit var categoryMapper: CategoryMapper
    @RelaxedMockK
    private lateinit var mealsMapper: MealsMapper
    @RelaxedMockK
    private lateinit var mealDetailMapper: MealDetailMapper

    private lateinit var category: List<CategoryDTO>
    private lateinit var meals: List<MealsDTO>
    private lateinit var mealDetail: List<MealDetailDTO>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
        repositoryImpl = MealRepositoryImpl(
            remoteDataSourceImpl,
            categoryMapper,
            mealsMapper,
            mealDetailMapper
        )
        category = getCategoryDTO()
        meals = getMealsDTO()
        mealDetail = getMealDetailDTO()
    }

    @Test
    fun getCategoryTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getCategories() } returns category

            val data = repositoryImpl.getCategories()

            data.collectLatest {
                if(it is Response.Success) {
                    Truth.assertThat(it.data).isEqualTo(getCategory())
                }
            }
        }
    }

    @Test
    fun getMealsListTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getMealsByCategory("Beef") } returns meals

            val data = repositoryImpl.getMealsByCategory("Beef")

            data.collectLatest {
                if(it is Response.Success) {
                    Truth.assertThat(it.data).isEqualTo(getMeals())
                }
            }
        }
    }

    @Test
    fun getMealsDetailTest() {
        runTest {
            coEvery { remoteDataSourceImpl.getMealById("52874") } returns mealDetail

            val data = repositoryImpl.getMealById("52874")

            data.collectLatest {
                if(it is Response.Success) {
                    Truth.assertThat(it.data).isEqualTo(listOf(getMealDetail()))
                }
            }
        }
    }
}