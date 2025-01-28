package com.synonym.androidblockexplorer.network

import com.synonym.androidblockexplorer.model.Block
import com.synonym.androidblockexplorer.model.BlockDetails
import com.synonym.androidblockexplorer.model.TransactionDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/blocks")
    suspend fun getRecentBlocks(): List<Block>

    @GET("api/block/{id}/txs")
    suspend fun getBlockTransactions(@Path("id") blockId: String): List<TransactionDetails>

}