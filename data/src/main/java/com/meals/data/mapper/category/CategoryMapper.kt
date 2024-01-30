package com.meals.data.mapper.category

import com.meals.data.mapper.ListMapper
import com.meals.data.model.CategoryDto
import com.meals.domain.entity.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor(): ListMapper<CategoryDto, Category> {

    override fun map(data: List<CategoryDto>) = data.map {
        it
    }.map { categoryDto ->
        Category(
            id = categoryDto.categoryID,
            category = categoryDto.categoryName,
            thumbImage = categoryDto.categoryThumb
        )
    }
}