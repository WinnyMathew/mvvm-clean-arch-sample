package com.example.mvvmcleanarchapp.presentation.ui.meals_list

import com.example.domain.entity.Meals


data class MealsListState(
    val isLoading: Boolean = false,
    val meals: List<Meals> = emptyList(),
    val error: String = ""
)
