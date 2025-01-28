package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MempoolTransactionsList(mempoolTxIds: Pair<List<String>, List<String>>) {
    val addedTxIds = mempoolTxIds.first
    val removedTxIds = mempoolTxIds.second

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Added Transactions", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(addedTxIds) { txid ->
                Text("Transaction ID: $txid", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Removed Transactions", style = MaterialTheme.typography.bodyMedium)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(removedTxIds) { txid ->
                Text("Transaction ID: $txid", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            }
        }
    }
}


