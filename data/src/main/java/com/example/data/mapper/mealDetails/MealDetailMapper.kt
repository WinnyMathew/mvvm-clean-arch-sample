package com.example.data.mapper.mealDetails

import com.example.data.mapper.ListMapper
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealsDTO
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals
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