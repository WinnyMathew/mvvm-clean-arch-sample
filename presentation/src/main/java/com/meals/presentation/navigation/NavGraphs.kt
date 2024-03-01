package com.meals.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.meals.data.utils.Constants.PARAM_ID_MEAL
import com.meals.data.utils.Constants.PARAM_STR_CATEGORY
import com.meals.presentation.ui.categoryList.CategoriesScreen
import com.meals.presentation.ui.mealDetail.MealDetailScreen
import com.meals.presentation.ui.mealsList.MealsScreen

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
            CategoriesScreen(navController = navController)
        }

        // Meals Screen
        composable(
            route = "${Screen.MealsScreen.route}/{$PARAM_STR_CATEGORY}",
            arguments = listOf(navArgument(PARAM_STR_CATEGORY) { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(PARAM_STR_CATEGORY)?.let {
                MealsScreen(navController = navController)
            }
        }

        // Meal Detail Screen
        composable(
            route = "${Screen.MealDetailScreen.route}/{$PARAM_ID_MEAL}",
            arguments = listOf(navArgument(PARAM_ID_MEAL) { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(PARAM_ID_MEAL)?.let {
                MealDetailScreen(navController = navController)
            }
        }
    }
}
