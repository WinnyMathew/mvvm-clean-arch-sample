package com.meals.presentation.ui.mapper.mealDetailUi

import com.meals.data.mapper.ListMapper
import com.meals.domain.entity.MealDetail
import com.meals.presentation.ui.model.MealDetailUi
import javax.inject.Inject

class MealDetailUiMapper @Inject constructor() : ListMapper<MealDetail, MealDetailUi> {
    override fun map(data: List<MealDetail>) = data.map { mealDetail ->
        with(mealDetail) {
            MealDetailUi(
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