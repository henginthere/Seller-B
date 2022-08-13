package com.ssafy.webrtc.openvidu

import android.view.View
import android.widget.TextView
import org.webrtc.SurfaceViewRenderer
import org.webrtc.VideoTrack

class RemoteParticipant(
    connectionId: String,
    participantName: String,
    session: Session
): Participant(connectionId,participantName,session){

    private var view: View? = null
    private var videoView: SurfaceViewRenderer? = null
    private lateinit var participantNameText: TextView

    init {
        getSession()?.addRemoteParticipant(this)
    }
    fun getVideoView():SurfaceViewRenderer? = videoView

    fun setVideoView(videoView: SurfaceViewRenderer){
        this.videoView = videoView
    }

    fun getView() = view

    fun setView(view: View){
        this.view = view
    }

    fun getParticipantNameText() : TextView { return participantNameText}

    fun setParticipantNameText(participantNameText: TextView){
        this.participantNameText = participantNameText
    }

    override fun dispose() { super.dispose()}
}