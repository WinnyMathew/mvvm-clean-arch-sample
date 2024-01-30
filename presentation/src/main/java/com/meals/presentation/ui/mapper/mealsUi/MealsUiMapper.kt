package com.meals.presentation.ui.mapper.mealsUi

import com.meals.data.mapper.ListMapper
import com.meals.domain.entity.Meals
import com.meals.presentation.ui.model.MealsUi
import javax.inject.Inject

class MealsUiMapper @Inject constructor() : ListMapper<Meals, MealsUi> {

    override fun map(data: List<Meals>) = data.map {
        it
    }.map { meals ->
        MealsUi(
            mealID = meals.mealID,
            meal = meals.meal,
            thumbImage = meals.thumbImage
        )
    }
}