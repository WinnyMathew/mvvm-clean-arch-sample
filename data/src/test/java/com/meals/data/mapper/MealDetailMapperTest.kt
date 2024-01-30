package com.meals.data.mapper

import com.meals.data.mapper.mealDetails.MealDetailMapper
import com.meals.data.util.getMealDetailDTO
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealDetailMapperTest {

    private lateinit var mealDetailMapper: MealDetailMapper

    @Before
    fun setUp() {
        mealDetailMapper = MealDetailMapper()
    }

    @Test
    fun mapCategoryDtoToCategory() {
        val mealDetail =
            mealDetailMapper.map(getMealDetailDTO())

        assertEquals("Beef and Mustard Pie", mealDetail[0].strMeal)
        assertEquals("Beef", mealDetail[0].strCategory)
        assertEquals("British", mealDetail[0].strArea)
    }
}