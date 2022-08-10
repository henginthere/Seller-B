package com.ssafy.webrtc.utils

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.webrtc.openvidu.RemoteParticipant
import org.webrtc.MediaStream
import org.webrtc.VideoTrack

fun AppCompatActivity.setRemoteMediaStream(
    stream: MediaStream,
    remoteParticipant: RemoteParticipant){

    val videoTrack :VideoTrack = stream.videoTracks.first()
    videoTrack.addSink(remoteParticipant.getVideoView())

    runOnUiThread{
        remoteParticipant.getVideoView().visibility = View.VISIBLE
    }
}


fun AppCompatActivity.createRemoteParticipantVideo(remoteParticipant : RemoteParticipant) {


}
