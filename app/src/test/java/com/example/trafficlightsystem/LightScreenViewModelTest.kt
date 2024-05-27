package com.example.trafficlightsystem

import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import com.example.trafficlightsystem.viewmodel.LightScreenViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any

class LightScreenViewModelTest {
    private lateinit var viewModel: LightScreenViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        viewModel = LightScreenViewModel()
    }

    @Test
    fun onChangeStateShouldReturnRedWhenNewColorIsRed() {
        //Given
        val newColor = Red

        //When
        viewModel.onChangeState(newColor = newColor, delay = any())

        //Then
        Assert.assertEquals(Red, viewModel.uiState.value.currentColor)
    }

    @Test
    fun onChangeStateShouldReturnRedWhenNewColorIsYellow() {
        //Given
        val newColor = Yellow

        //When
        viewModel.onChangeState(newColor = newColor, delay = any())

        //Then
        Assert.assertEquals(Yellow, viewModel.uiState.value.currentColor)
    }

    @Test
    fun onChangeStateShouldReturnRedWhenNewColorIsGreen() {
        //Given
        val newColor = Green

        //When
        viewModel.onChangeState(newColor = newColor, delay = any())

        //Then
        Assert.assertEquals(Green, viewModel.uiState.value.currentColor)
    }
}
