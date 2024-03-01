package com.meals.presentation.ui

sealed interface SideEffect<out T> {
    data class OnItemClickNavigateToNextScreen<out T>(val value: T): SideEffect<T>
}