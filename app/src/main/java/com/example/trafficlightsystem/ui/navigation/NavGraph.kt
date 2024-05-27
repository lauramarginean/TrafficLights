package com.example.trafficlightsystem.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.trafficlightsystem.TopLevelDestination
import com.example.trafficlightsystem.ui.screens.LightsScreen

fun NavGraphBuilder.navGraph(navController: NavController) {
    navigation(
        startDestination = TopLevelDestination.Home.route,
        route = MAIN_ROUTE
    ) {
        composable("$LIGHTS_SCREEN/{$VARIANT_OPTION}") { backStackEntry ->
            backStackEntry.arguments?.getString(VARIANT_OPTION)?.let {
                LightsScreen(
                    navController = navController,
                    carModel = it
                )
            }
        }
    }
}

const val MAIN_ROUTE = "main"
const val LIGHTS_SCREEN = "lightsScreen"
const val VARIANT_OPTION = "variantOption"
