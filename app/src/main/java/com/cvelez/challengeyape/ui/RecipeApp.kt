package com.cvelez.challengeyape.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.cvelez.challengeyape.ui.theme.RecipeTheme

@Composable
fun RecipesApp(
    darkMode: MutableState<Boolean>
) {
    RecipeTheme(darkTheme = darkMode.value) {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            RecipeActions(navController)
        }

        RecipeNavGraph(
            navController = navController,
            navigateToHome = navigationActions.navigateToHome,
            navigateToMap = navigationActions.navigateToMapWithLocation,
            navigateToDetail = navigationActions.navigateToDetail,
            darkMode = darkMode
        )
    }
}