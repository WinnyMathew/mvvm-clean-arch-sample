package com.meals.presentation.ui.mealDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meals.data.utils.Constants.PARAM_ID_MEAL
import com.meals.domain.Response
import com.meals.domain.usecase.GetMealUseCase
import com.meals.presentation.ui.UserMealIntent
import com.meals.presentation.ui.mapper.mealDetailUi.MealDetailUiMapper
import com.meals.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val mealDetailUiMapper: MealDetailUiMapper,
    private val coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state.asStateFlow()
    init {
        savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
            handleIntent(UserMealIntent.GetMealDetail, idMeal)
        }
    }

    private fun handleIntent(userMealIntent: UserMealIntent, idMeal: String) {
        if (userMealIntent == UserMealIntent.GetMealDetail) {
            getMeal(idMeal)
        }
    }

    internal fun getMeal(idMeal: String) {
        viewModelScope.launch(coroutineContextProvider.IO) {
            _state.value = MealDetailState(isLoading = true)
            when (val result = getMealUseCase(idMeal)) {
                is Response.Success -> {
                    _state.value = MealDetailState(
                        meals = mealDetailUiMapper.map(result.data ?: emptyList())
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