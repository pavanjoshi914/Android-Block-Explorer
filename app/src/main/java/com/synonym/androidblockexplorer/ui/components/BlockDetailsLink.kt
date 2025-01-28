package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlockDetailsLink(showDetails: () -> Unit, title: String = "View Block Details") {
    TextButton(onClick = showDetails) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}
