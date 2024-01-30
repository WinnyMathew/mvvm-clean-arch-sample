package com.meals.presentation.ui.mealsList

import androidx.lifecycle.SavedStateHandle
import com.meals.data.utils.Constants
import com.meals.domain.Response
import com.meals.domain.repository.MealRepository
import com.meals.domain.usecase.GetMealsUseCase
import com.meals.presentation.ui.getMeals
import com.meals.presentation.ui.mapper.mealsUi.MealsUiMapper
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

private const val CATEGORY_STRING = "Beef"
class MealsListViewModelTest {
    private val repository: MealRepository = mockk()
    private lateinit var getMealsUseCase: GetMealsUseCase
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var savedStateHandle: SavedStateHandle
    private val mealsUiMapper = MealsUiMapper()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        getMealsUseCase = GetMealsUseCase(repository)
        savedStateHandle = SavedStateHandle(mapOf(Constants.PARAM_STR_CATEGORY to CATEGORY_STRING))
    }

    @Test
    fun `WHEN getMeals invoked THEN Loading State is RETURNED`() = runTest {
        val expectedResult = Response.Loading
        coEvery { repository.getMealsByCategory(CATEGORY_STRING)} returns expectedResult

        val viewModel = MealsListViewModel(getMealsUseCase, mealsUiMapper, savedStateHandle)
        viewModel.getMeals(CATEGORY_STRING)

        backgroundScope.launch {
            viewModel.state.collect{}
        }

        assertTrue(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.meals.isEmpty())
    }

    @Test
    fun `WHEN getMeals invoked THEN Meals list is RETURNED`() = runTest {
        val expectedResult = Response.Success(getMeals())
        coEvery { repository.getMealsByCategory(CATEGORY_STRING)} returns expectedResult

        val viewModel = MealsListViewModel(getMealsUseCase, mealsUiMapper, savedStateHandle)
        viewModel.getMeals(CATEGORY_STRING)

        backgroundScope.launch {
            viewModel.state.collect{}
        }

        assertFalse(viewModel.state.value.isLoading)
        assert(viewModel.state.value.meals.size == 2)
        assertEquals(viewModel.state.value.meals[0].meal,"Beef and Mustard Pie")
    }

    @Test
    fun `WHEN getMeals invoked THEN Error is RETURNED`() = runTest {
        val expectedResult = Response.Error("Error")
        coEvery { repository.getMealsByCategory(CATEGORY_STRING)} returns expectedResult

        val viewModel = MealsListViewModel(getMealsUseCase, mealsUiMapper, savedStateHandle)
        viewModel.getMeals(CATEGORY_STRING)

        backgroundScope.launch {
            viewModel.state.collect{}
        }

        assertFalse(viewModel.state.value.isLoading)
        assert(viewModel.state.value.error == "Error")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}