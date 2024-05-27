package com.example.trafficlightsystem

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isDisplayed
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.trafficlightsystem.ui.screens.CarModel
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.kotlin.any

class HomeScreenTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun carModelTest() {
        composeTestRule.setContent {
            CarModel(any())
        }

        // Finding and controlling the UI component
        val textField = composeTestRule.onNodeWithText("Enter your car model")
        assertTrue(textField.isDisplayed())

        val outlinedTextField = composeTestRule.onNodeWithText("Car model")
        assertTrue(outlinedTextField.isDisplayed())

        val button = composeTestRule.onNodeWithText("Start Driving")
        button.assertIsNotEnabled()

        // Interacting with the UI component
        outlinedTextField.performTextInput("BM")
        button.assertIsNotEnabled()

        outlinedTextField.performTextInput("BMW")
        button.assertIsEnabled()
    }
}
