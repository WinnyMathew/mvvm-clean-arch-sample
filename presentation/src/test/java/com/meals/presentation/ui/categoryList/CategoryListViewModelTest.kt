package com.meals.presentation.ui.categoryList

import com.meals.domain.Response
import com.meals.domain.repository.MealRepository
import com.meals.domain.usecase.GetCategoriesUseCase
import com.meals.presentation.ui.getCategory
import com.meals.presentation.ui.mapper.categoryUi.CategoryUiMapper
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
import org.junit.Before
import org.junit.Test

class CategoryListViewModelTest {
    private val repository: MealRepository = mockk()
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var testDispatcher: TestDispatcher
    private val categoryUiMapper = CategoryUiMapper()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        getCategoriesUseCase = GetCategoriesUseCase((repository))
    }

    @Test
    fun `WHEN getCategory invoked THEN Loading state is RETURNED`() = runTest {
        val expectedResult = Response.Loading
        coEvery { repository.getCategories() } returns expectedResult

        val viewModel = CategoryListViewModel(getCategoriesUseCase, categoryUiMapper)
        viewModel.getCategories()

        backgroundScope.launch {
            viewModel.state.collect{}
        }

        assert(viewModel.state.value.isLoading)
        assert(viewModel.state.value.categories.isEmpty())
    }

    @Test
    fun `WHEN getCategory invoked THEN Category list is RETURNED`() = runTest {
        val expectedResult = Response.Success(getCategory())
        coEvery { repository.getCategories() } returns expectedResult

        val viewModel = CategoryListViewModel(getCategoriesUseCase, categoryUiMapper)
        viewModel.getCategories()

        backgroundScope.launch {
            viewModel.state.collect{}
        }
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.categories[0].category == "Beef")
        assert(viewModel.state.value.categories.size == 2)
    }


    @Test
    fun `WHEN getCategory invoked THEN Error is RETURNED`() = runTest {
        val expectedResult = Response.Error("Error")
        coEvery { repository.getCategories() } returns expectedResult

        val viewModel = CategoryListViewModel(getCategoriesUseCase, categoryUiMapper)
        viewModel.getCategories()

        backgroundScope.launch {
            viewModel.state.collect {}
        }
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.error == "Error")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}