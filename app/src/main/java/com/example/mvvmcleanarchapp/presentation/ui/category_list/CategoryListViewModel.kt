package com.example.mvvmcleanarchapp.presentation.ui.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Response
import com.example.domain.use_case.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CategoryListState())
    val state: StateFlow<CategoryListState> = _state.asStateFlow()

    init {
        getCategories()
    }

    internal fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collectLatest { result ->
                when (result) {
                    is Response.Success -> {
                        _state.value = CategoryListState(
                            categories = result.data ?: emptyList()
                        )
                    }
                    is Response.Error -> {
                        _state.value = CategoryListState(
                            error = result.message ?: "An unexpected error occurred"
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