package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synonym.androidblockexplorer.model.TransactionDetails
import com.synonym.androidblockexplorer.network.ApiClient


@Composable
fun BlockTransactionsList(blockId: String) {
    val transactions = remember { mutableStateOf<List<TransactionDetails>?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(blockId) {
        try {
            val response = ApiClient.service.getBlockTransactions(blockId)
            transactions.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading.value = false
        }
    }

    if (isLoading.value) {
        CircularProgressIndicator()
    } else {
        transactions.value?.let { txList ->
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                items(txList) { tx ->
                    TransactionCard(transaction = tx)
                }
            }
        } ?: run {
            Text("No transactions found", modifier = Modifier.padding(16.dp))
        }
    }
}
