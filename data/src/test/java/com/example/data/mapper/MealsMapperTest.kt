package com.example.data.mapper

import com.example.data.mapper.meals.MealsMapper
import com.example.data.util.getMealsDTO
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealsMapperTest {
    private lateinit var mealMapper: MealsMapper

    @Before
    fun setUp() {
        mealMapper = MealsMapper()
    }

    @Test
    fun mapCategoryDtoToCategory() {
        val meals =
            mealMapper.map(getMealsDTO())

        Assert.assertEquals("Beef and Mustard Pie", meals[0].meal)
        Assert.assertEquals(2, meals.size)
    }
}