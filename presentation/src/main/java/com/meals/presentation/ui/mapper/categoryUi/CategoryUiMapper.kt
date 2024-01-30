package com.meals.presentation.ui.mapper.categoryUi

import com.meals.data.mapper.ListMapper
import com.meals.domain.entity.Category
import com.meals.presentation.ui.model.CategoryUi
import javax.inject.Inject

class CategoryUiMapper @Inject constructor(): ListMapper<Category, CategoryUi> {

    override fun map(data: List<Category>) = data.map {
        it
    }.map { category ->
        CategoryUi(
            id = category.id,
            category = category.category,
            thumbImage = category.thumbImage
        )
    }
}