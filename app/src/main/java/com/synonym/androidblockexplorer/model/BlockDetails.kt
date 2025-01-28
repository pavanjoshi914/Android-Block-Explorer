package com.synonym.androidblockexplorer.model

data class BlockDetails(
    val id: String,
    val height: Int,
    val timestamp: Long,
    val size: Int,
    val transactions: List<String>,
    val block_hash: String
)
