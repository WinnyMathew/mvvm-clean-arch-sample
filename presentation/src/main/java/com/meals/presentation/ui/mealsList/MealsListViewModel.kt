package com.meals.presentation.ui.mealsList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meals.data.utils.Constants.PARAM_STR_CATEGORY
import com.meals.domain.Response
import com.meals.domain.usecase.GetMealsUseCase
import com.meals.presentation.ui.mapper.mealsUi.MealsUiMapper
import com.meals.presentation.ui.mealsList.MealListScreenIntent.GetMealsList
import com.meals.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val mealsUiMapper: MealsUiMapper,
    private val coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MealsListState())
    val state: StateFlow<MealsListState> = _state.asStateFlow()
    init {
        savedStateHandle.get<String>(PARAM_STR_CATEGORY)?.let { strCategory ->
            handleIntent(GetMealsList, strCategory)
        }
    }

    private fun handleIntent(mealListScreenIntent: MealListScreenIntent, strCategory: String) {
        when (mealListScreenIntent) {
            is GetMealsList -> getMeals(strCategory)
        }
    }

    internal fun getMeals(strCategory: String) {
        viewModelScope.launch(coroutineContextProvider.IO) {
            _state.value = MealsListState(isLoading = true)
            when (val result = getMealsUseCase(strCategory)) {
                is Response.Success -> {
                    _state.value = MealsListState(
                        meals = mealsUiMapper.map(result.data)
                    )
                }

                is Response.Error -> {
                    _state.value = MealsListState(
                        error = result.message
                    )
                }

                is Response.Loading -> {
                    _state.value = MealsListState(isLoading = true)
                }
            }
        }
    }
}