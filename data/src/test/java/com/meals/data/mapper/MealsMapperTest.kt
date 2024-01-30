package com.meals.data.mapper

import com.meals.data.mapper.meals.MealsMapper
import com.meals.data.util.getMealsDTO
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealsMapperTest {
    private lateinit var mealMapper: MealsMapper

    @Before
    fun setUp() {
        mealMapper = MealsMapper()
    }

    @Test
    fun mapMealsDtoToMealsList() {
        val meals =
            mealMapper.map(getMealsDTO())

        assertEquals("Beef and Mustard Pie", meals[0].meal)
        assertEquals(2, meals.size)
    }
}