package com.example.presentation.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Response
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.presentation.ui.UserMealIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    val userIntent = Channel<UserMealIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(CategoryListState())
    val state: StateFlow<CategoryListState> = _state.asStateFlow()

    init {
        handleIntent()
    }
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                if (it == UserMealIntent.GetMealCategories) {
                    getCategories()
                }
            }
        }
    }

    internal fun getCategories() {
        viewModelScope.launch {
            _state.value = CategoryListState(isLoading = true)
            when (val result = getCategoriesUseCase.invoke()) {
                is Response.Success -> {
                    _state.value = CategoryListState(
                        categories = result.data ?: emptyList()
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