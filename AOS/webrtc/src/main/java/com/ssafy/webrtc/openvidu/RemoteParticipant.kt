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

    private lateinit var view: View
    private lateinit var videoView: SurfaceViewRenderer
    private lateinit var participantNameText: TextView

    fun getVideoView():SurfaceViewRenderer = videoView

}