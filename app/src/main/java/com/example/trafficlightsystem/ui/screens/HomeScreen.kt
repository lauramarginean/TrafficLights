package com.example.trafficlightsystem.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.trafficlightsystem.R
import com.example.trafficlightsystem.ui.navigation.LIGHTS_SCREEN
import com.example.trafficlightsystem.ui.theme.TrafficLightSystemTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    TrafficLightSystemTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    GlideImage(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .height(240.dp),
                        model = R.drawable.car_model,
                        contentDescription = "Product image",
                        contentScale = ContentScale.FillWidth
                    )

                    CarModel(navController)
                }
            }
        }
    }
}

@Composable
fun CarModel(navController: NavController) {
    var carError by remember { mutableStateOf(value = false) }
    val carModel = remember { mutableStateOf("") }

    Text(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        text = stringResource(R.string.enter_your_car_model),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )

    OutlinedTextField(
        value = carModel.value,
        onValueChange = {
            carModel.value = it
            carError = it.length < THREE
        },
        label = { Text(text = stringResource(R.string.car_model)) },
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        isError = carError,
        supportingText = {
            Text(
                text = if (carError) stringResource(R.string.please_enter_a_valid_car_model) else EMPTY_STRING,
                color = MaterialTheme.colorScheme.error
            )
        }
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        onClick = {
            navController.navigate("$LIGHTS_SCREEN/${carModel.value}")
        },
        enabled = !carError && carModel.value.isNotEmpty()
    ) {
        Text(
            text = stringResource(R.string.start_driving),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

const val THREE = 3
const val EMPTY_STRING = ""
