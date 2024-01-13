package com.example.mvvmcleanarchapp.presentation.ui.meals_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constants.PARAM_STR_CATEGORY
import com.example.domain.Response
import com.example.domain.use_case.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MealsListState())
    val state: StateFlow<MealsListState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(PARAM_STR_CATEGORY)?.let { strCategory ->
            getMeals(strCategory)
        }
    }

    internal fun getMeals(strCategory: String) {
        viewModelScope.launch {
            getMealsUseCase(strCategory).collectLatest { result ->
                when (result) {
                    is Response.Success -> {
                        _state.value = MealsListState(
                            meals = result.data ?: emptyList()
                        )
                    }

                    is Response.Error -> {
                        _state.value = MealsListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Response.Loading -> {
                        _state.value = MealsListState(isLoading = true)
                    }
                }
            }
        }
    }
}