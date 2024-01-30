package com.meals.data.mapper.mealDetails

import com.meals.data.mapper.ListMapper
import com.meals.data.model.MealDetailDTO
import com.meals.domain.entity.MealDetail
import javax.inject.Inject

class MealDetailMapper @Inject constructor() : ListMapper<MealDetailDTO, MealDetail> {

    override fun map(data: List<MealDetailDTO>) = data.map {
        it
    }.map { mealDetail ->
        MealDetail(
            idMeal = mealDetail.idMeal,
            strMeal = mealDetail.strMeal,
            strCategory = mealDetail.strCategory,
            strArea = mealDetail.strArea,
            strInstructions = mealDetail.strInstructions,
            strMealThumb = mealDetail.strMealThumb
        )

    }
}