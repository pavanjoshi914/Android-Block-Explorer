package com.synonym.androidblockexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.synonym.androidblockexplorer.ui.screens.MainScreen
import com.synonym.androidblockexplorer.ui.theme.AndroidBlockExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBlockExplorerTheme {
                MainScreen()
            }
        }
    }
}
