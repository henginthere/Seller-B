package com.ssafy.webrtc.websocket

import android.app.Activity
import android.os.AsyncTask
import com.neovisionaries.ws.client.*

class CustomWebSocket2(

) : AsyncTask<Activity, Void, Void>(), WebSocketListener{

    override fun doInBackground(vararg p0: Activity?): Void {
        TODO("Not yet implemented")
    }

    override fun onStateChanged(websocket: WebSocket?, newState: WebSocketState?) {
        TODO("Not yet implemented")
    }

    override fun onConnected(
        websocket: WebSocket?,
        headers: MutableMap<String, MutableList<String>>?
    ) {
        TODO("Not yet implemented")
    }

    override fun onConnectError(websocket: WebSocket?, cause: WebSocketException?) {
        TODO("Not yet implemented")
    }

    override fun onDisconnected(
        websocket: WebSocket?,
        serverCloseFrame: WebSocketFrame?,
        clientCloseFrame: WebSocketFrame?,
        closedByServer: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun onFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onContinuationFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onTextFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onBinaryFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onCloseFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onPingFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onPongFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onTextMessage(websocket: WebSocket?, text: String?) {
        TODO("Not yet implemented")
    }

    override fun onTextMessage(websocket: WebSocket?, data: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onBinaryMessage(websocket: WebSocket?, binary: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onSendingFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onFrameSent(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onFrameUnsent(websocket: WebSocket?, frame: WebSocketFrame?) {
        TODO("Not yet implemented")
    }

    override fun onThreadCreated(websocket: WebSocket?, threadType: ThreadType?, thread: Thread?) {
        TODO("Not yet implemented")
    }

    override fun onThreadStarted(websocket: WebSocket?, threadType: ThreadType?, thread: Thread?) {
        TODO("Not yet implemented")
    }

    override fun onThreadStopping(websocket: WebSocket?, threadType: ThreadType?, thread: Thread?) {
        TODO("Not yet implemented")
    }

    override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
        TODO("Not yet implemented")
    }

    override fun onFrameError(
        websocket: WebSocket?,
        cause: WebSocketException?,
        frame: WebSocketFrame?
    ) {
        TODO("Not yet implemented")
    }

    override fun onMessageError(
        websocket: WebSocket?,
        cause: WebSocketException?,
        frames: MutableList<WebSocketFrame>?
    ) {
        TODO("Not yet implemented")
    }

    override fun onMessageDecompressionError(
        websocket: WebSocket?,
        cause: WebSocketException?,
        compressed: ByteArray?
    ) {
        TODO("Not yet implemented")
    }

    override fun onTextMessageError(
        websocket: WebSocket?,
        cause: WebSocketException?,
        data: ByteArray?
    ) {
        TODO("Not yet implemented")
    }

    override fun onSendError(
        websocket: WebSocket?,
        cause: WebSocketException?,
        frame: WebSocketFrame?
    ) {
        TODO("Not yet implemented")
    }

    override fun onUnexpectedError(websocket: WebSocket?, cause: WebSocketException?) {
        TODO("Not yet implemented")
    }

    override fun handleCallbackError(websocket: WebSocket?, cause: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onSendingHandshake(
        websocket: WebSocket?,
        requestLine: String?,
        headers: MutableList<Array<String>>?
    ) {
        TODO("Not yet implemented")
    }

}