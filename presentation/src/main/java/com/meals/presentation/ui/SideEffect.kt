package com.meals.presentation.ui

sealed class SideEffect<out T> {
    data class OnItemClickNavigateToNextScreen<out T>(val value: T): SideEffect<T>()
}