package com.synonym.androidblockexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synonym.androidblockexplorer.model.Block

@Composable
fun BlockDetailsTable(block: Block) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Text(
                text = "Block Details",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(listOf(
            "ID: " to block.id.toString(),
            "Timestamp: " to block.timestamp.toString(),
            "Height: " to block.height.toString(),
            "Version: " to block.version.toString(),
            "Bits: " to block.bits.toString(),
            "Nonce: " to block.nonce.toString(),
            "Difficulty: " to block.difficulty.toString(),
            "Merkle Root: " to block.merkle_root,
            "Tx Count: " to block.tx_count.toString(),
            "Size: " to block.size.toString(),
            "Weight: " to block.weight.toString(),
            "Previous Block Hash: " to block.previousblockhash
        )) { (label, value) ->
            BlockDetailRow(label = label, value = value.toString())
        }

        item {
            Text(
                text = "Extras: ",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(listOf(
            "Coinbase Raw: " to block.extras.coinbaseRaw,
            "Median Fee: " to block.extras.medianFee,
            "Fee Range: " to block.extras.feeRange.joinToString(", "),
            "Reward: " to block.extras.reward,
            "Total Fees: " to block.extras.totalFees,
            "Avg Fee: " to block.extras.avgFee,
            "Avg Fee Rate: " to block.extras.avgFeeRate,
            "Pool Name: " to block.extras.pool.name
        )) { (label, value) ->
            BlockDetailRow(label = label, value = value.toString())
        }
    }
}

@Composable
fun BlockDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
