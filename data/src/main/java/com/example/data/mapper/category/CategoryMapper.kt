package com.example.data.mapper.category

import com.example.data.mapper.ListMapper
import com.example.data.model.CategoryDTO
import com.example.domain.entity.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor(): ListMapper<CategoryDTO, Category> {

    override fun map(data: List<CategoryDTO>) = data.map {
        it
    }.map { categoryDto ->
        Category(
            id = categoryDto.idCategory,
            category = categoryDto.strCategory,
            thumbImage = categoryDto.strCategoryThumb
        )
    }
}