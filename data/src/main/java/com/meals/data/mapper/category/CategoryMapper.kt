package com.meals.data.mapper.category

import com.meals.data.mapper.ListMapper
import com.meals.data.model.CategoryDto
import com.meals.domain.entity.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() : ListMapper<CategoryDto, Category> {
    override fun map(data: List<CategoryDto>) = data.map { categoryDto ->
        with(categoryDto) {
            Category(
                id = categoryID,
                category = categoryName,
                thumbImage = categoryThumb
            )
        }
    }
}