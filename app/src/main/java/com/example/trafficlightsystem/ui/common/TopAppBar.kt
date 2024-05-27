package com.example.trafficlightsystem.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    onNavigateBack: () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        colors = colors,
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                modifier = Modifier.padding()
            )
        }, navigationIcon = {
            IconButton(onClick = {
                onNavigateBack()
            }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back button"
                )
            }

        }, actions = actions)
}
