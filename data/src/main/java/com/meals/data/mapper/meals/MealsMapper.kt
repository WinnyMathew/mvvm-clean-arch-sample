package com.meals.data.mapper.meals

import com.meals.data.mapper.ListMapper
import com.meals.data.model.MealsDto
import com.meals.domain.entity.Meals
import javax.inject.Inject

class MealsMapper @Inject constructor() : ListMapper<MealsDto, Meals> {
    override fun map(data: List<MealsDto>) = data.map { meals ->
        with(meals) {
            Meals(
                mealID = id,
                meal = mealName,
                thumbImage = mealThumb
            )
        }
    }
}