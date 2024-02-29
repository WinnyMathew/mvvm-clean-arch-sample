package com.meals.presentation.ui.mealDetail

import androidx.lifecycle.SavedStateHandle
import com.meals.data.utils.Constants
import com.meals.domain.Response
import com.meals.domain.repository.MealRepository
import com.meals.domain.usecase.GetMealUseCase
import com.meals.presentation.ui.getMealDetail
import com.meals.presentation.ui.mapper.mealDetailUi.MealDetailUiMapper
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

private const val MEAL_ID = "52874"
class MealDetailViewModelTest {
    private val repository: MealRepository = mockk()
    private lateinit var getMealUseCase: GetMealUseCase
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var savedStateHandle: SavedStateHandle
    private val mealDetailUiMapper = MealDetailUiMapper()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        getMealUseCase = GetMealUseCase(repository)
        savedStateHandle = SavedStateHandle(mapOf(Constants.PARAM_ID_MEAL to MEAL_ID))
    }

    @Test
    fun `WHEN getMeal invoked THEN Loading State is RETURNED`() {
        val expectedResult = Response.Loading
        coEvery { repository.getMealById(MEAL_ID)} returns expectedResult

        val viewModel = MealDetailViewModel(
            getMealUseCase = getMealUseCase,
            mealDetailUiMapper = mealDetailUiMapper,
            savedStateHandle = savedStateHandle
        )

        runTest {
            viewModel.getMeal(MEAL_ID)
        }

        assertTrue(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.meals.isEmpty())
    }

    @Test
    fun `WHEN getMeal invoked THEN MealDetail list is RETURNED`() {
        val expectedResult = Response.Success(listOf(getMealDetail()))
        coEvery { repository.getMealById(MEAL_ID)} returns expectedResult

        val viewModel = MealDetailViewModel(
            getMealUseCase = getMealUseCase,
            mealDetailUiMapper = mealDetailUiMapper,
            savedStateHandle = savedStateHandle
        )

        runTest {
            viewModel.getMeal(MEAL_ID)
        }

        assertFalse(viewModel.state.value.isLoading)
        assertEquals("Beef and Mustard Pie", viewModel.state.value.meals[0].strMeal)
        assertEquals("Beef", viewModel.state.value.meals[0].strCategory)
        assertEquals("British", viewModel.state.value.meals[0].strArea)
    }

    @Test
    fun `WHEN getMeal invoked THEN Error is RETURNED`() {
        val expectedResult = Response.Error("Error")
        coEvery { repository.getMealById(MEAL_ID) } returns expectedResult

        val viewModel = MealDetailViewModel(
            getMealUseCase = getMealUseCase,
            mealDetailUiMapper = mealDetailUiMapper,
            savedStateHandle = savedStateHandle
        )

        runTest {
            viewModel.getMeal(MEAL_ID)
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