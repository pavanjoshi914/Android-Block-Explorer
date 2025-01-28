package com.synonym.androidblockexplorer.model

data class Block(
    val id: String,
    val timestamp: Long,
    val height: Int,
    val version: Int,
    val bits: Int,
    val nonce: Long,
    val difficulty: Double,
    val merkle_root: String,
    val tx_count: Int,
    val size: Int,
    val weight: Int,
    val previousblockhash: String,
    val extras: BlockExtras
)

data class BlockExtras(
    val coinbaseRaw: String,
    val medianFee: Int,
    val feeRange: List<Int>,
    val reward: Long,
    val totalFees: Long,
    val avgFee: Int,
    val avgFeeRate: Int,
    val pool: BlockPool
)

data class BlockPool(
    val id: Int,
    val name: String,
    val slug: String
)
