package com.meals.presentation.ui.mapper.categoryUi

import com.meals.presentation.ui.getCategory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CategoryUiMapperTest {
    private lateinit var categoryUiMapper: CategoryUiMapper

    @Before
    fun setUp() {
        categoryUiMapper = CategoryUiMapper()
    }

    @Test
    fun mapCategoryToCategoryUi() {
        val category =
            categoryUiMapper.map(getCategory())

        assertEquals("Beef", category[0].category)
        assertEquals(2, category.size)
    }
}