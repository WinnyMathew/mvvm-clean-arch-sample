package com.meals.presentation.ui.mapper.mealsUi

import com.meals.presentation.ui.getMeals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealsUiMapperTest {
    private lateinit var mealsUiMapper: MealsUiMapper

    @Before
    fun setUp() {
        mealsUiMapper = MealsUiMapper()
    }

    @Test
    fun mapMealsListToMealsUi() {
        val meals =
            mealsUiMapper.map(getMeals())

        assertEquals("Beef and Mustard Pie", meals[0].meal)
        assertEquals(2, meals.size)
    }
}