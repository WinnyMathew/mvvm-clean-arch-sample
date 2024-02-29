package com.meals.presentation.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineContextProvider @Inject constructor() {
    val IO: CoroutineDispatcher by lazy { Dispatchers.IO }
}
