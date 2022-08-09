package com.ssafy.webrtc.openvidu

import android.content.Context
import org.webrtc.*

class LocalParticipant(
    participantName: String,
    session: Session,
    private val context: Context,
    private val surfaceViewRenderer: SurfaceViewRenderer
) : Participant(participantName, session) {

    private lateinit var surfaceTextureHelper: SurfaceTextureHelper
    private lateinit var videoCapturer: VideoCapturer

    private val localIceCandidates: Collection<IceCandidate> = mutableListOf()
    private lateinit var localSessionDescription: SessionDescription

    init {

    }

    fun startCamera(){
        val eglBaseContext = EglBase.create().eglBaseContext
    }

}