package com.meals.presentation.ui.mealDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meals.data.utils.Constants.PARAM_ID_MEAL
import com.meals.domain.Response
import com.meals.domain.usecase.GetMealUseCase
import com.meals.presentation.ui.mapper.mealDetailUi.MealDetailUiMapper
import com.meals.presentation.ui.mealDetail.MealDetailScreenIntent.GetMealDetail
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
    private val _state = MutableStateFlow(MealDetailState(isLoading = true))
    val state: StateFlow<MealDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
            handleIntent(GetMealDetail, idMeal)
        }
    }

    private fun handleIntent(mealDetailIntent: MealDetailScreenIntent, idMeal: String) {
        when (mealDetailIntent) {
            GetMealDetail -> getMeal(idMeal)
        }
    }

    internal fun getMeal(idMeal: String) {
        viewModelScope.launch(coroutineContextProvider.IO) {
            getMealUseCase(idMeal).also { result ->
                when (result) {
                    is Response.Success -> {
                        _state.value = MealDetailState(
                            meals = mealDetailUiMapper.map(result.data)
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
}