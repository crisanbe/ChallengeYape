package com.cvelez.challengeyape.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cvelez.challengeyape.ui.detail.DetailScreen
import com.cvelez.challengeyape.ui.home.HomeScreen
import com.cvelez.challengeyape.ui.location.GoogleMaps

@Composable
fun RecipeNavGraph(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToMap: (Double, Double) -> Unit,
    navigateToDetail: (Int) -> Unit,
    darkMode: MutableState<Boolean>,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                darkMode = darkMode,
                onItemClicked = { navigateToDetail(it) }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            DetailScreen(
                upPress = navigateToHome,
                navController = navController
            )
        }
        composable(route = Screen.Map.route,
            arguments = listOf(
                navArgument("latitude") { type = NavType.StringType
                    defaultValue = "6.317450"},
                navArgument("latitude") { type = NavType.StringType
                    defaultValue = "-75.565558"
                }
            )
        ) {
            GoogleMaps(
                latitud = it.arguments?.getString("latitude").orEmpty(),
                longitud = it.arguments?.getString("longitude").orEmpty()
            )
        }
    }
}