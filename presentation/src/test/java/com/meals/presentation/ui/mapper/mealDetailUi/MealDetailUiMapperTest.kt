package com.meals.presentation.ui.mapper.mealDetailUi

import com.meals.presentation.ui.getMealDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealDetailUiMapperTest {
    private lateinit var mealDetailUiMapper: MealDetailUiMapper

    @Before
    fun setUp() {
        mealDetailUiMapper = MealDetailUiMapper()
    }

    @Test
    fun mapMealDetailToMealDetailUi() {
        val mealDetail =
            mealDetailUiMapper.map(listOf(getMealDetail()))

        assertEquals("Beef and Mustard Pie", mealDetail[0].strMeal)
        assertEquals("Beef", mealDetail[0].strCategory)
        assertEquals("British", mealDetail[0].strArea)
    }
}