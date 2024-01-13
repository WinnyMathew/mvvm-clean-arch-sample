package com.example.mvvmcleanarchapp.presentation.ui.meal_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constants.PARAM_ID_MEAL
import com.example.domain.Response
import com.example.domain.use_case.GetMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
            getMeal(idMeal)
        }
    }

    internal fun getMeal(idMeal: String) {
        viewModelScope.launch {
            getMealUseCase(idMeal).collectLatest { result ->
                when (result) {
                    is Response.Success -> {
                        _state.value = MealDetailState(
                            meals = result.data ?: emptyList()
                        )
                    }

                    is Response.Error -> {
                        _state.value = MealDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Response.Loading -> {
                        _state.value = MealDetailState(isLoading = true)
                    }
                }
            }
        }
    }
}