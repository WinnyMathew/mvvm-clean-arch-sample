package com.meals.data.mapper.meals

import com.meals.data.mapper.ListMapper
import com.meals.data.model.MealsDTO
import com.meals.domain.entity.Meals
import javax.inject.Inject

class MealsMapper @Inject constructor() : ListMapper<MealsDTO, Meals> {

    override fun map(data: List<MealsDTO>) = data.map {
        it
    }.map { meals ->
        Meals(
            mealID = meals.id,
            meal = meals.mealName,
            thumbImage = meals.mealThumb
        )
    }
}