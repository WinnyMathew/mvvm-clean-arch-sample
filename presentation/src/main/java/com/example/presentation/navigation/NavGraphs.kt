package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.utils.Constants.PARAM_ID_MEAL
import com.example.data.utils.Constants.PARAM_STR_CATEGORY
import com.example.presentation.ui.categoryList.CategoriesScreen
import com.example.presentation.ui.mealDetail.MealDetailScreen
import com.example.presentation.ui.mealsList.MealsScreen

@Composable
fun NavGraphs() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CategoriesScreen.route
    ) {

        // Categories Screen
        composable(
            route = Screen.CategoriesScreen.route
        ) {
            CategoriesScreen(
                onCategoryClick = { strCategory ->
                    navController.navigate("${Screen.MealsScreen.route}/${strCategory}")
                }
            )
        }

        // Meals Screen
        composable(
            route = "${Screen.MealsScreen.route}/{$PARAM_STR_CATEGORY}",
            arguments = listOf(navArgument(PARAM_STR_CATEGORY) { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("strCategory")?.let {
                MealsScreen(
                    navController = navController,
                    onMealItemClick = { idMeal ->
                        navController.navigate("${Screen.MealDetailScreen.route}/${idMeal}")
                    }
                )
            }
        }

        // Meal Detail Screen
        composable(
            route = "${Screen.MealDetailScreen.route}/{$PARAM_ID_MEAL}",
            arguments = listOf(navArgument(PARAM_ID_MEAL) { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("idMeal")?.let {
                MealDetailScreen(navController = navController)
            }
        }
    }
}
