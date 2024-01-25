package com.example.presentation.ui.categoryList

import com.example.domain.Response
import com.example.domain.repository.MealRepository
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.presentation.ui.getCategory
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        getCategoriesUseCase = GetCategoriesUseCase((repository))
    }

    @Test
    fun `WHEN getCategory invoked THEN Category list is RETURNED`() = runTest {
        val expectedResult = Response.Success(getCategory())
        coEvery { repository.getCategories() } returns expectedResult

        val viewModel = CategoryListViewModel(getCategoriesUseCase)
        viewModel.getCategories()

        backgroundScope.launch {
            viewModel.state.collect{}
        }

        assert(viewModel.state.value.categories[0].category == "Beef")
        assert(viewModel.state.value.categories.size == 2)
    }

    @Test
    fun `WHEN getCategory invoked THEN Error is RETURNED`() = runTest {
        val expectedResult = Response.Error("Error")
        coEvery { repository.getCategories() } returns expectedResult

        val viewModel = CategoryListViewModel(getCategoriesUseCase)
        viewModel.getCategories()

        backgroundScope.launch {
            viewModel.state.collect {}
        }

        assert(viewModel.state.value.error == "Error")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}