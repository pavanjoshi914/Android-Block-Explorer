package com.synonym.androidblockexplorer.model

data class TransactionDetails(
    val txid: String,
    val fee: Long,
    val size: Int,
    val weight: Int,
    val inputs: List<TransactionInput>,
    val outputs: List<TransactionOutput>
)

data class TransactionInput(
    val prevout: TransactionOutput?,
    val scriptsig: String,
    val sequence: Long
)

data class TransactionOutput(
    val value: Long,
    val scriptpubkey: String,
    val scriptpubkey_address: String
)
