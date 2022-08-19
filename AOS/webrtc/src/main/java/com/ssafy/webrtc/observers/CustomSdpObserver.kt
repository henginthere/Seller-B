package com.ssafy.webrtc.observers

import android.util.Log
import org.webrtc.SdpObserver
import org.webrtc.SessionDescription

open class CustomSdpObserver(
    private val tag: String
) : SdpObserver {

    private fun log(s: String) {
        Log.d(tag, s)
    }

    override fun onCreateSuccess(p0: SessionDescription?) {
        log("onCreateSuccess " + p0.toString())
    }

    override fun onSetSuccess() = log("onSetSuccess ")

    override fun onCreateFailure(p0: String?) = log("onCreateFailure " + p0)

    override fun onSetFailure(p0: String?) = log("onSetFailure" + p0)

}