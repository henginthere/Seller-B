package com.ssafy.webrtc.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
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

@SuppressLint("ResourceType")
fun AppCompatActivity.test(@LayoutRes res: Int,){
    val textView: TextView = findViewById(res)

}
