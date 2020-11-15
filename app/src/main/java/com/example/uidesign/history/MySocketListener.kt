package com.example.uidesign.history

import okhttp3.Response
import okhttp3.WebSocket

interface MySocketListener {
    fun onOpen(webSocket: WebSocket, response: Response)
    fun onMessage(webSocket: WebSocket, text: String)
}