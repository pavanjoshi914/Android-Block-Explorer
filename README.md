# Android Bitcoin Block Explorer

This repository is an open-source **Bitcoin Block Explorer** built for Android using **Kotlin** and **Jetpack Compose**. It provides a modern, lightweight, and user-friendly interface to explore Bitcoin blocks, transactions, and addresses directly on your Android device.

## Features

### 1. **Get Recent Block in Real-time**
- **Real-time Updates**: The app fetches and displays the latest mined Bitcoin block as soon as it's available.
- **Auto-refresh**: The block information is updated automatically as new blocks are mined, ensuring you always have the most recent data.

### 2. **Show All Information of Bitcoin Block**
- **Comprehensive Block Data**: View full details of a Bitcoin block, including the block hash, block height, timestamp, previous block hash, and more.
- **Header and Transaction Details**: Access detailed information about the block header and the transactions it contains.

### 3. **Show Relevant Transactions of Blocks**
- **Transaction List**: For each block, view a list of all relevant transactions.
- **Detailed Transaction Info**: See the transaction IDs, input/output addresses, amounts, and other transaction details.

### 4. **Block Transaction Information**
- **Transaction Breakdown**: For each block, see a detailed breakdown of each transaction, including the sender, recipient, amount, and fee.
- **Transaction Fee**: View the transaction fee and total inputs/outputs for each transaction.

### 5. **Live Transactions Contributing to Mempool**
- **Mempool Overview**: Watch live transactions that are part of the Bitcoin mempool waiting to be included in a block.
- **Real-time Mempool Updates**: Transactions are updated in real-time as they are added or removed from the mempool.


## Architecture
The app utilizes WebSocket to subscribe to real-time Bitcoin block and transaction updates. It follows the ViewModel and LiveData architecture for efficient and lifecycle-conscious data management. The block and transaction data are modeled using structured data classes, ensuring a seamless flow of information between the WebSocket service, ViewModel, and UI components. This flexible and scalable design makes it easy to integrate potential improvements, such as address tracking and displaying multiple recent blocks, without affecting the core functionality.

## Potential Improvements

1. **Address Tracking**
    - Implement a feature to allow users to track specific Bitcoin addresses. Users could input a Bitcoin address, and the app would monitor it for any incoming or outgoing transactions.

2. **Show More Than One Recent Block**
    - Extend the app to show a list of multiple recent blocks instead of just the latest block. This would allow users to view the past few blocks and their related transactions in real-time.

