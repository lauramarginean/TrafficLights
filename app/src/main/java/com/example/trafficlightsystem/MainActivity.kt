package com.example.trafficlightsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trafficlightsystem.ui.navigation.navGraph
import com.example.trafficlightsystem.ui.screens.HomeScreen
import com.example.trafficlightsystem.ui.theme.TrafficLightSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrafficLightSystemTheme {
                TrafficLightSystemApp()
            }
        }
    }
}

@Composable
fun TrafficLightSystemApp() {
    val navController = rememberNavController()
    Scaffold( modifier = Modifier.navigationBarsPadding())
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevelDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            navGraph(navController = navController)
            composable(TopLevelDestination.Home.route) {
                HomeScreen(navController)
            }
        }
    }
}

enum class TopLevelDestination(
    var route: String
) {
    Home( route = "home_route")
}
