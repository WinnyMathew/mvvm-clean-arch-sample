package com.meals.data.mapper

import com.meals.data.mapper.category.CategoryMapper
import com.meals.data.util.getCategoryDTO
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CategoryMapperTest {

    private lateinit var categoryMapper: CategoryMapper

    @Before
    fun setUp() {
        categoryMapper = CategoryMapper()
    }

    @Test
    fun mapCategoryDtoToCategory() {
        val category =
            categoryMapper.map(getCategoryDTO())

        assertEquals("Beef", category[0].category)
        assertEquals(2, category.size)
    }
}