package com.meals.presentation.ui.mapper.mealsUi

import com.meals.data.mapper.ListMapper
import com.meals.domain.entity.Meals
import com.meals.presentation.ui.model.MealsUi
import javax.inject.Inject

class MealsUiMapper @Inject constructor() : ListMapper<Meals, MealsUi> {
    override fun map(data: List<Meals>) = data.map { meals ->
        with(meals) {
            MealsUi(
                mealID = mealID,
                meal = meal,
                thumbImage = thumbImage
            )
        }
    }
}