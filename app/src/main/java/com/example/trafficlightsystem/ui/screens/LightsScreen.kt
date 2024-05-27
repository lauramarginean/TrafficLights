package com.example.trafficlightsystem.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trafficlightsystem.viewmodel.ILightViewModel
import com.example.trafficlightsystem.viewmodel.LightScreenViewModel
import com.example.trafficlightsystem.ui.common.TopAppBar
import com.example.trafficlightsystem.ui.theme.DarkGreen
import com.example.trafficlightsystem.ui.theme.DarkRed
import com.example.trafficlightsystem.ui.theme.DarkYellow
import com.example.trafficlightsystem.ui.theme.TrafficLightSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightsScreen(navController: NavController, carModel: String, viewModel: ILightViewModel = viewModel<LightScreenViewModel>(),) {
    val uiState = viewModel.uiState.collectAsState()

    TrafficLightSystemTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            topBar = {
                TopAppBar(
                    title = carModel,
                    navController::popBackStack
                )
            },
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .wrapContentSize(Alignment.TopCenter)
                ) {
                    RedBox(currentColor = uiState.value.currentColor, viewModel)
                    YellowBox(currentColor = uiState.value.currentColor, viewModel)
                    GreenBox(currentColor = uiState.value.currentColor, viewModel)
                }

            }
        }
    }
}

@Composable
private fun RedBox(currentColor: Color, viewModel: ILightViewModel) {
    if (currentColor == MyColors.Red.color) {
        viewModel.onChangeState(MyColors.Yellow.color, 4000)
        LightBox(initialColor = MyDarkColors.Red.color, targetColor = currentColor, duration = 2000)
    } else {
        DarkBox(color = MyDarkColors.Red.color)
    }
}

@Composable
private fun YellowBox(currentColor: Color, viewModel: ILightViewModel) {
    if (currentColor == MyColors.Yellow.color) {
        viewModel.onChangeState(MyColors.Green.color, 2000)
        LightBox(initialColor = MyDarkColors.Yellow.color, targetColor = currentColor, duration = 1000)
    } else {
        DarkBox(color = MyDarkColors.Yellow.color)
    }
}

@Composable
private fun GreenBox(currentColor: Color, viewModel: ILightViewModel) {
    if (currentColor == MyColors.Green.color) {
        viewModel.onChangeState(MyColors.Red.color, 4000)
        LightBox(initialColor = MyDarkColors.Green.color, targetColor = currentColor, duration = 2000)
    } else {
        DarkBox(color = MyDarkColors.Green.color)
    }

}

@Composable
private fun LightBox(initialColor: Color, targetColor: Color, duration: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = initialColor,
        targetValue = targetColor,
        animationSpec = infiniteRepeatable(tween(duration), RepeatMode.Reverse),
        label = "color"
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
            .clip(CircleShape)
            .background(animatedColor)
    )
}

@Composable
private fun DarkBox(color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
            .clip(CircleShape)
            .background(color)
    )
}

enum class MyColors(val color: Color) {
    Red(Color.Red), Yellow(Color.Yellow), Green(Color.Green)
}

enum class MyDarkColors(val color: Color) {
    Red(DarkRed), Yellow(DarkYellow), Green(DarkGreen)
}
