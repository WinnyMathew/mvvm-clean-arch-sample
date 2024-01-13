package com.example.mvvmcleanarchapp.presentation.ui.meal_detail

import androidx.lifecycle.SavedStateHandle
import com.example.data.utils.Constants
import com.example.domain.Response
import com.example.domain.use_case.GetMealUseCase
import com.example.mvvmcleanarchapp.presentation.ui.getMealDetail
import com.example.mvvmcleanarchapp.presentation.ui.meal_list.CATEGORY_STRING
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val MEAL_ID = "52874"
class MealDetailViewModelTest {

    @RelaxedMockK
    private lateinit var getMealUseCase: GetMealUseCase
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        savedStateHandle = SavedStateHandle(mapOf(Constants.PARAM_STR_CATEGORY to CATEGORY_STRING))
    }

    @Test
    fun getCategoryListTest() = runTest {
        val expectedResult = Response.Success(listOf(getMealDetail()))
        coEvery { getMealUseCase(MEAL_ID) } returns flowOf(expectedResult)

        // When
        val viewModel = MealDetailViewModel(getMealUseCase, savedStateHandle)
        viewModel.getMeal(MEAL_ID)

        // Then
        viewModel.state.collectLatest {
            assertEquals("Beef and Mustard Pie", it.meals[0].strMeal)
            assertEquals("Beef", it.meals[0].strCategory)
            assertEquals("British", it.meals[0].strArea)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}