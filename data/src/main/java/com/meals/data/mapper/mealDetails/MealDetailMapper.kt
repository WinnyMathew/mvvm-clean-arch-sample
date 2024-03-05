package com.meals.data.mapper.mealDetails

import com.meals.data.mapper.ListMapper
import com.meals.data.model.MealDetailDto
import com.meals.domain.entity.MealDetail
import javax.inject.Inject

class MealDetailMapper @Inject constructor() : ListMapper<MealDetailDto, MealDetail> {
    override fun map(data: List<MealDetailDto>) = data.map { mealDetail ->
        with(mealDetail) {
            MealDetail(
                idMeal = idMeal,
                strMeal = strMeal,
                strCategory = strCategory,
                strArea = strArea,
                strInstructions = strInstructions,
                strMealThumb = strMealThumb
            )
        }
    }
}