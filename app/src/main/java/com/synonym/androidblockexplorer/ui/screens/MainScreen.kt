package com.synonym.androidblockexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synonym.androidblockexplorer.ui.components.BlockCard
import com.synonym.androidblockexplorer.ui.components.BlockDetailsLink
import com.synonym.androidblockexplorer.websocket.WebSocketManager
import com.synonym.androidblockexplorer.model.Block
import com.synonym.androidblockexplorer.model.BlockExtras
import com.synonym.androidblockexplorer.model.BlockPool
import com.synonym.androidblockexplorer.model.TransactionDetails
import com.synonym.androidblockexplorer.network.ApiClient
import com.synonym.androidblockexplorer.ui.components.BlockDetailsTable
import com.synonym.androidblockexplorer.ui.components.BlockTransactionsList
import com.synonym.androidblockexplorer.ui.components.MempoolTransactionsList

@Composable
fun MainScreen() {
    val blockHeight = remember { mutableStateOf("Loading...") }
    val blockData = remember {
        mutableStateOf(
            Block(
                id = "0",
                timestamp = System.currentTimeMillis(),
                height = 0,
                version = 0,
                bits = 0,
                nonce = 0,
                difficulty = 0.0,
                merkle_root = "",
                tx_count = 0,
                size = 0,
                weight = 0,
                previousblockhash = "",
                extras = BlockExtras(
                    coinbaseRaw = "",
                    medianFee = 0,
                    feeRange = emptyList(),
                    reward = 0L,
                    totalFees = 0L,
                    avgFee = 0,
                    avgFeeRate = 0,
                    pool = BlockPool(id = 0, name = "", slug = "")
                )
            )
        )
    }
    val isLoading = remember { mutableStateOf(true) }
    val showSection = remember { mutableStateOf("NONE") }
    val mempoolTxIds = remember { mutableStateOf(Pair(emptyList<String>(), emptyList<String>())) }

    LaunchedEffect(Unit) {
        WebSocketManager(blockHeight, blockData, isLoading, mempoolTxIds ).start()
    }

    if (isLoading.value) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BlockCard(blockHeight = blockHeight.value)

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
                "DETAILS" -> BlockDetailsTable(block = blockData.value)
                "TRANSACTIONS" -> BlockTransactionsList(blockId = blockData.value.id)
                "MEMPOOL" -> MempoolTransactionsList(mempoolTxIds)
            }
        }
    }
}
