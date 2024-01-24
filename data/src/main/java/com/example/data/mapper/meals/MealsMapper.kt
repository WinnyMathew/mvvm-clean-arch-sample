package com.example.data.mapper.meals

import com.example.data.mapper.ListMapper
import com.example.data.model.MealsDTO
import com.example.domain.entity.Meals
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