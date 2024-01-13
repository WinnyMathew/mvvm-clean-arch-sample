package com.example.mvvmcleanarchapp.presentation.ui.category_list

import com.example.domain.Response
import com.example.domain.use_case.GetCategoriesUseCase
import com.example.mvvmcleanarchapp.presentation.ui.getCategory
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
import org.junit.Before
import org.junit.Test

class CategoryListViewModelTest {

    @RelaxedMockK
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var testDispatcher: TestDispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getCategoryListTest() = runTest {
        val expectedResult = Response.Success(getCategory())
        coEvery { getCategoriesUseCase() } returns flowOf(expectedResult)

        // When
        val viewModel = CategoryListViewModel(getCategoriesUseCase)
        viewModel.getCategories()

        // Then
        viewModel.state.collectLatest {
            assert(it.categories[0].category == "Beef")
            assert(it.categories.size == 2)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}