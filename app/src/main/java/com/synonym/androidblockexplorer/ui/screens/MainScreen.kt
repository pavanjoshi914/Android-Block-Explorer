package com.synonym.androidblockexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.synonym.androidblockexplorer.model.Block
import com.synonym.androidblockexplorer.ui.components.BlockCard
import com.synonym.androidblockexplorer.ui.components.BlockDetailsLink
import com.synonym.androidblockexplorer.ui.components.BlockDetailsTable
import com.synonym.androidblockexplorer.ui.components.BlockTransactionsList
import com.synonym.androidblockexplorer.ui.components.MempoolTransactionsList

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()

    // Observe LiveData from ViewModel
    val blockHeight by viewModel.blockHeight.observeAsState("Loading...")
    val blockData by viewModel.blockData.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val mempoolTxIds by viewModel.mempoolTxIds.observeAsState(Pair(emptyList(), emptyList()))

    // Start the WebSocket connection
    LaunchedEffect(Unit) {
        viewModel.startWebSocket()
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        val showSection = remember { mutableStateOf("DETAILS") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BlockCard(blockHeight = blockHeight)

            BlockDetailsLink(showDetails = { showSection.value = "DETAILS" })
            BlockDetailsLink(
                showDetails = { showSection.value = "TRANSACTIONS" },
                title = "View Transactions in current Block"
            )
            BlockDetailsLink(
                showDetails = { showSection.value = "MEMPOOL" },
                title = "View Live Mempool Transactions"
            )

            when (showSection.value) {
                "DETAILS" -> blockData?.let { BlockDetailsTable(block = it) }
                "TRANSACTIONS" -> BlockTransactionsList(blockId = blockData?.id ?: "")
                "MEMPOOL" -> MempoolTransactionsList(mempoolTxIds)
            }
        }
    }
}
