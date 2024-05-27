package com.example.trafficlightsystem.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trafficlightsystem.ui.screens.MyColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ILightViewModel{
    val uiState: StateFlow<LightUIState>

    fun onChangeState(newColor: Color, delay: Long)

    data class LightUIState(
        val currentColor: Color
    )
}

class LightScreenViewModel :  ViewModel(), ILightViewModel {
    private val _uiState = MutableStateFlow(ILightViewModel.LightUIState(MyColors.Red.color))
    override val uiState: StateFlow<ILightViewModel.LightUIState> =
        _uiState.asStateFlow()


    override fun onChangeState(newColor: Color, delay: Long) {
        viewModelScope.launch {
            delay(delay)
            _uiState.update { currentState -> currentState.copy(currentColor = newColor) }
        }
    }
}
