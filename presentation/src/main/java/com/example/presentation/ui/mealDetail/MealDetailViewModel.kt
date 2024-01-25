package com.example.presentation.ui.mealDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constants.PARAM_ID_MEAL
import com.example.domain.Response
import com.example.domain.usecase.GetMealUseCase
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
class MealDetailViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userIntent = Channel<UserMealIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state.asStateFlow()
    init {
        savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
            handleIntent(idMeal)
        }
    }

    private fun handleIntent(idMeal: String) {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is UserMealIntent.GetMealDetail -> getMeal(idMeal)
                    else -> {
                        // Do Nothing
                    }
                }
            }
        }
    }
    internal fun getMeal(idMeal: String) {
        viewModelScope.launch {
            _state.value = MealDetailState(isLoading = true)
            when (val result = getMealUseCase(idMeal)) {
                is Response.Success -> {
                    _state.value = MealDetailState(
                        meals = result.data ?: emptyList()
                    )
                }

                is Response.Error -> {
                    _state.value = MealDetailState(
                        error = result.message
                    )
                }

                is Response.Loading -> {
                    _state.value = MealDetailState(isLoading = true)
                }
            }
        }
    }
}