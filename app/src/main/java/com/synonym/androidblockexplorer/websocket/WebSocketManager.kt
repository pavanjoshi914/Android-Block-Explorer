package com.synonym.androidblockexplorer.websocket

import android.os.Handler
import android.os.Looper
import okhttp3.*
import okio.ByteString
import org.json.JSONObject
import com.synonym.androidblockexplorer.ui.screens.MainViewModel
import org.json.JSONArray

class WebSocketManager(
    private val viewModel: MainViewModel
) {

    private val client = OkHttpClient()
    private val mainHandler = Handler(Looper.getMainLooper())

    fun start() {
        val request = Request.Builder()
            .url("wss://mempool.space/api/v1/ws")
            .build()

        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                subscribeToWebSocket(webSocket)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                println(text)
                handleWebSocketMessage(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {}

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {}

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {}

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                t.printStackTrace()
            }
        }

        client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()
    }

    private fun subscribeToWebSocket(webSocket: WebSocket) {
        val blocksSubscribeMessage = """{"action":"want","data":["blocks"]}"""
        val txidsSubscribeMessage = """{"track-mempool-txids": true}"""

        webSocket.send(blocksSubscribeMessage)
        webSocket.send(txidsSubscribeMessage)
    }

    private fun handleWebSocketMessage(text: String) {
        try {
            val responseJson = JSONObject(text)
            handleBlocksData(responseJson)
            handleMempoolTxids(responseJson)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handleBlocksData(responseJson: JSONObject) {
        val blocksArray = responseJson.optJSONArray("blocks")
        if (blocksArray != null && blocksArray.length() > 0) {
            val block = blocksArray.getJSONObject(blocksArray.length() - 1)
            val newBlockHeight = block.optInt("height", -1).takeIf { it != -1 }?.toString() ?: "Unknown"
            val newBlock = BlockMapper.mapJsonToBlock(block)

            mainHandler.post {
                viewModel.updateBlockHeight(newBlockHeight)
                viewModel.updateBlockData(newBlock)
                if (viewModel.isLoading.value == true) {
                    viewModel.setLoading(false)
                }
            }
        }
    }

    private fun handleMempoolTxids(responseJson: JSONObject) {
        val txidsJson = responseJson.optJSONObject("mempool-txids")
        if (txidsJson != null) {
            val addedTxids = txidsJson.optJSONArray("added")
            val removedTxids = txidsJson.optJSONArray("removed")

            val addedTxidList = parseTxids(addedTxids)
            val removedTxidList = parseTxids(removedTxids)

            mainHandler.post {
                viewModel.updateMempoolTxIds(addedTxidList.take(10).reversed(), removedTxidList.take(10).reversed())
            }
        }
    }

    private fun parseTxids(txidsArray: JSONArray?): List<String> {
        val txidList = mutableListOf<String>()
        txidsArray?.let {
            for (i in 0 until it.length()) {
                val txid = it.getString(i)
                txidList.add(txid)
            }
        }
        return txidList
    }
}
