package com.synonym.androidblockexplorer.websocket

import com.synonym.androidblockexplorer.model.Block
import com.synonym.androidblockexplorer.model.BlockExtras
import com.synonym.androidblockexplorer.model.BlockPool
import org.json.JSONObject

class BlockMapper {
    companion object {
        fun mapJsonToBlock(blockJson: JSONObject): Block {
            return Block(
                id = blockJson.optString("id"),
                timestamp = blockJson.optLong("timestamp"),
                height = blockJson.optInt("height"),
                version = blockJson.optInt("version"),
                bits = blockJson.optInt("bits"),
                nonce = blockJson.optLong("nonce"),
                difficulty = blockJson.optDouble("difficulty"),
                merkle_root = blockJson.optString("merkle_root"),
                tx_count = blockJson.optInt("tx_count"),
                size = blockJson.optInt("size"),
                weight = blockJson.optInt("weight"),
                previousblockhash = blockJson.optString("previousblockhash"),
                extras = mapJsonToBlockExtras(blockJson.optJSONObject("extras"))
            )
        }

        private fun mapJsonToBlockExtras(extrasJson: JSONObject?): BlockExtras {
            return BlockExtras(
                coinbaseRaw = extrasJson?.optString("coinbaseRaw") ?: "",
                medianFee = extrasJson?.optInt("medianFee") ?: 0,
                feeRange = extrasJson?.optJSONArray("feeRange")?.let { feeRange ->
                    List(feeRange.length()) { feeRange.getInt(it) }
                } ?: emptyList(),
                reward = extrasJson?.optLong("reward") ?: 0L,
                totalFees = extrasJson?.optLong("totalFees") ?: 0L,
                avgFee = extrasJson?.optInt("avgFee") ?: 0,
                avgFeeRate = extrasJson?.optInt("avgFeeRate") ?: 0,
                pool = mapJsonToBlockPool(extrasJson?.optJSONObject("pool"))
            )
        }

        private fun mapJsonToBlockPool(poolJson: JSONObject?): BlockPool {
            return BlockPool(
                id = poolJson?.optInt("id") ?: 0,
                name = poolJson?.optString("name") ?: "",
                slug = poolJson?.optString("slug") ?: ""
            )
        }
    }
}
