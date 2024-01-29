package com.example.data.mapper

import com.example.data.mapper.mealDetails.MealDetailMapper
import com.example.data.util.getMealDetailDTO
import org.junit.Assert
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

        Assert.assertEquals("Beef and Mustard Pie", mealDetail[0].strMeal)
        Assert.assertEquals("Beef", mealDetail[0].strCategory)
        Assert.assertEquals("British", mealDetail[0].strArea)
    }
}