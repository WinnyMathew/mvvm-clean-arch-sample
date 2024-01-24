package com.example.data.util

import com.example.data.model.CategoryDTO
import com.example.data.model.MealDetailDTO
import com.example.data.model.MealsDTO
import com.example.domain.entity.Category
import com.example.domain.entity.MealDetail
import com.example.domain.entity.Meals

fun getCategoryDTO() = mutableListOf<CategoryDTO>().apply {
    add(
        CategoryDTO(
            categoryID = "1",
            categoryName = "Beef",
            categoryThumb = "https://www.themealdb.com/images/category/beef.png"
        )
    )
    add(
        CategoryDTO(
            categoryID = "2",
            categoryName = "Chicken",
            categoryThumb = "https://www.themealdb.com/images/category/chicken.png"
        )
    )
}.toList()


fun getMealsDTO() = mutableListOf<MealsDTO>().apply {
    add(
        MealsDTO(
            id = "52874",
            mealName = "Beef and Mustard Pie",
            mealThumb = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"
        )
    )
    add(
        MealsDTO(
            id = "52878",
            mealName = "Beef and Oyster pie",
            mealThumb = "https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg"
        )
    )
}.toList()

fun getMealDetailDTO() = mutableListOf<MealDetailDTO>().apply {
    add(
        MealDetailDTO(
            idMeal = "52874",
            strMeal = "Beef and Mustard Pie",
            strCategory = "Beef",
            strArea = "British",
            strInstructions = "Preheat the oven to 150C/300F/Gas 2.\n" +
                    "Toss the beef and flour together in a bowl with some salt and black pepper.\n" +
                    "Heat a large casserole until hot, add half of the rapeseed oil and enough of the beef to just cover the bottom of the casserole.\n" +
                    "Fry until browned on each side, then remove and set aside. Repeat with the remaining oil and beef.\n" +
                    "Return the beef to the pan, add the wine and cook until the volume of liquid has reduced by half, then add the stock, onion, carrots, thyme and mustard, and season well with salt and pepper.\n" +
                    "Cover with a lid and place in the oven for two hours.\n" +
                    "Remove from the oven, check the seasoning and set aside to cool. Remove the thyme.\n" +
                    "When the beef is cool and you're ready to assemble the pie, preheat the oven to 200C/400F/Gas 6.\n" +
                    "Transfer the beef to a pie dish, brush the rim with the beaten egg yolks and lay the pastry over the top. Brush the top of the pastry with more beaten egg.\n" +
                    "Trim the pastry so there is just enough excess to crimp the edges, then place in the oven and bake for 30 minutes, or until the pastry is golden-brown and cooked through.\n" +
                    "For the green beans, bring a saucepan of salted water to the boil, add the beans and cook for 4-5 minutes, or until just tender.\n" +
                    "Drain and toss with the butter, then season with black pepper.\n" +
                    "To serve, place a large spoonful of pie onto each plate with some green beans alongside.",
            strMealThumb = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"
        )
    )
}


fun getCategory() = mutableListOf<Category>().apply {
    add(
        Category(
            id = "1",
            category = "Beef",
            thumbImage = "https://www.themealdb.com/images/category/beef.png"
        )
    )
    add(
        Category(
            id = "2",
            category = "Chicken",
            thumbImage = "https://www.themealdb.com/images/category/chicken.png"
        )
    )
}.toList()


fun getMeals() = mutableListOf<Meals>().apply {
    add(
        Meals(
            mealID = "52874",
            meal = "Beef and Mustard Pie",
            thumbImage = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"
        )
    )
    add(
        Meals(
            mealID = "52878",
            meal = "Beef and Oyster pie",
            thumbImage = "https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg"
        )
    )
}.toList()

fun getMealDetail() = MealDetail(
    idMeal = "52874",
    strMeal = "Beef and Mustard Pie",
    strCategory = "Beef",
    strArea = "British",
    strInstructions = "Preheat the oven to 150C/300F/Gas 2.\n" +
            "Toss the beef and flour together in a bowl with some salt and black pepper.\n" +
            "Heat a large casserole until hot, add half of the rapeseed oil and enough of the beef to just cover the bottom of the casserole.\n" +
            "Fry until browned on each side, then remove and set aside. Repeat with the remaining oil and beef.\n" +
            "Return the beef to the pan, add the wine and cook until the volume of liquid has reduced by half, then add the stock, onion, carrots, thyme and mustard, and season well with salt and pepper.\n" +
            "Cover with a lid and place in the oven for two hours.\n" +
            "Remove from the oven, check the seasoning and set aside to cool. Remove the thyme.\n" +
            "When the beef is cool and you're ready to assemble the pie, preheat the oven to 200C/400F/Gas 6.\n" +
            "Transfer the beef to a pie dish, brush the rim with the beaten egg yolks and lay the pastry over the top. Brush the top of the pastry with more beaten egg.\n" +
            "Trim the pastry so there is just enough excess to crimp the edges, then place in the oven and bake for 30 minutes, or until the pastry is golden-brown and cooked through.\n" +
            "For the green beans, bring a saucepan of salted water to the boil, add the beans and cook for 4-5 minutes, or until just tender.\n" +
            "Drain and toss with the butter, then season with black pepper.\n" +
            "To serve, place a large spoonful of pie onto each plate with some green beans alongside.",
    strMealThumb = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"
)