package com.meals.presentation.ui.mapper.mealDetailUi

import com.meals.data.mapper.ListMapper
import com.meals.data.model.MealDetailDto
import com.meals.domain.entity.MealDetail
import com.meals.presentation.ui.model.MealDetailUi
import javax.inject.Inject

class MealDetailUiMapper @Inject constructor() : ListMapper<MealDetail, MealDetailUi> {

    override fun map(data: List<MealDetail>) = data.map {
        it
    }.map { mealDetail ->
        MealDetailUi(
            idMeal = mealDetail.idMeal,
            strMeal = mealDetail.strMeal,
            strCategory = mealDetail.strCategory,
            strArea = mealDetail.strArea,
            strInstructions = mealDetail.strInstructions,
            strMealThumb = mealDetail.strMealThumb
        )

    }
}