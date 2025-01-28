package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlockCard(blockHeight: String) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(150.dp)
            .background(Color.Gray, RoundedCornerShape(CornerSize(16.dp)))
            .clickable { /* Add any action here */ },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Block No: $blockHeight",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}
