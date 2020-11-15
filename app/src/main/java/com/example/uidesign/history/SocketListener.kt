package com.example.uidesign.history

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

 class SocketListener(private val socketListener: MySocketListener) :
    WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        socketListener.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        socketListener.onMessage(webSocket, text)
    }
}