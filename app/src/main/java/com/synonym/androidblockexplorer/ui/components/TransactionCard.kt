package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synonym.androidblockexplorer.model.TransactionDetails

@Composable
fun TransactionCard(transaction: TransactionDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Transaction ID: ${transaction.txid}", style = MaterialTheme.typography.bodyMedium)
            Text("Fee: ${transaction.fee}", style = MaterialTheme.typography.bodyMedium)
            Text("Size: ${transaction.size}", style = MaterialTheme.typography.bodyMedium)
            Text("Weight: ${transaction.weight}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}