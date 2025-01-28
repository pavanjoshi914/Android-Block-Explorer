package com.synonym.androidblockexplorer.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.synonym.androidblockexplorer.model.Block
import com.synonym.androidblockexplorer.model.BlockPool
import com.synonym.androidblockexplorer.model.BlockExtras
import com.synonym.androidblockexplorer.websocket.WebSocketManager

class MainViewModel : ViewModel() {
    private val _blockHeight = MutableLiveData<String>("Loading...")
    val blockHeight: LiveData<String> get() = _blockHeight

    private val _blockData = MutableLiveData<Block>(
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
    val blockData: LiveData<Block> get() = _blockData

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _mempoolTxIds = MutableLiveData<Pair<List<String>, List<String>>>(
        Pair(emptyList(), emptyList())
    )
    val mempoolTxIds: LiveData<Pair<List<String>, List<String>>> get() = _mempoolTxIds

    private val webSocketManager: WebSocketManager = WebSocketManager(this)

    fun startWebSocket() {
        webSocketManager.start()
    }

    fun updateBlockHeight(newHeight: String) {
        _blockHeight.value = newHeight
    }

    fun updateBlockData(newBlock: Block) {
        _blockData.value = newBlock
    }

    fun updateMempoolTxIds(addedTxids: List<String>, removedTxids: List<String>) {
        _mempoolTxIds.value = Pair(addedTxids, removedTxids)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}
