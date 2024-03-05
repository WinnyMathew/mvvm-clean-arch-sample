package com.meals.presentation.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meals.domain.Response
import com.meals.domain.usecase.GetCategoriesUseCase
import com.meals.presentation.ui.SideEffect
import com.meals.presentation.ui.categoryList.CategoryScreenIntent.CategoryItemClick
import com.meals.presentation.ui.categoryList.CategoryScreenIntent.GetMealCategories
import com.meals.presentation.ui.mapper.categoryUi.CategoryUiMapper
import com.meals.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val categoryUiMapper: CategoryUiMapper,
    private val coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider()
) : ViewModel() {
    private val _state = MutableStateFlow(CategoryListState(isLoading = true))
    val state: StateFlow<CategoryListState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SideEffect<String>>(extraBufferCapacity = 1)
    val sideEffect: SharedFlow<SideEffect<String>> = _sideEffect.asSharedFlow()

    init {
        handleIntent(GetMealCategories)
    }

    internal fun handleIntent(categoryScreenIntent: CategoryScreenIntent) {
        when (categoryScreenIntent) {
            GetMealCategories -> getCategories()
            is CategoryItemClick -> {
                _sideEffect.tryEmit(
                    SideEffect.OnItemClickNavigateToNextScreen(
                        categoryScreenIntent.strCategory
                    )
                )
            }
        }
    }

    internal fun getCategories() {
        viewModelScope.launch(coroutineContextProvider.IO) {
            getCategoriesUseCase().also { result ->
                when (result) {
                    is Response.Success -> {
                        _state.value = CategoryListState(
                            categories = categoryUiMapper.map(result.data)
                        )
                    }

                    is Response.Error -> {
                        _state.value = CategoryListState(
                            error = result.message
                        )
                    }

                    is Response.Loading -> {
                        _state.value = CategoryListState(isLoading = true)
                    }
                }
            }
        }
    }
}