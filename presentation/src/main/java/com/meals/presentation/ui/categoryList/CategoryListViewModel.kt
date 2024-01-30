package com.meals.presentation.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meals.domain.Response
import com.meals.domain.usecase.GetCategoriesUseCase
import com.meals.presentation.ui.UserMealIntent
import com.meals.presentation.ui.mapper.categoryUi.CategoryUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val categoryUiMapper: CategoryUiMapper
): ViewModel() {
    private val _state = MutableStateFlow(CategoryListState())
    val state: StateFlow<CategoryListState> = _state.asStateFlow()

    init {
        handleIntent(UserMealIntent.GetMealCategories)
    }

    private fun handleIntent(userMealIntent: UserMealIntent) {
        if (userMealIntent == UserMealIntent.GetMealCategories) {
            getCategories()
        }
    }

    internal fun getCategories() {
        viewModelScope.launch {
            _state.value = CategoryListState(isLoading = true)
            when (val result = getCategoriesUseCase.invoke()) {
                is Response.Success -> {
                    _state.value = CategoryListState(
                        categories = categoryUiMapper.map(result.data ?: emptyList())
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