package com.example.mvvmcleanarchapp.presentation.ui.meal_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.data.utils.Constants
import com.example.domain.Response
import com.example.domain.use_case.GetMealsUseCase
import com.example.mvvmcleanarchapp.presentation.ui.getMeals
import com.example.mvvmcleanarchapp.presentation.ui.meals_list.MealsListViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

const val CATEGORY_STRING = "Beef"
class MealListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getMealsUseCase: GetMealsUseCase
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        savedStateHandle = SavedStateHandle(mapOf(Constants.PARAM_STR_CATEGORY to CATEGORY_STRING))
    }

    @Test
    fun getCategoryListTest() = runTest {
        val expectedResult = Response.Success(getMeals())
        coEvery { getMealsUseCase(CATEGORY_STRING) } returns flowOf(expectedResult)

        // When
        val viewModel = MealsListViewModel(getMealsUseCase, savedStateHandle)
        viewModel.getMeals(CATEGORY_STRING)

        // Then
        viewModel.state.collectLatest {
            assert(it.meals[0].meal == "Beef and Mustard Pie")
            assert(it.meals.size == 2)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}