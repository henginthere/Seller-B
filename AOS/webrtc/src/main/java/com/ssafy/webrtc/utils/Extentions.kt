package com.ssafy.webrtc.utils

import android.annotation.SuppressLint
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.webrtc.openvidu.RemoteParticipant
import okhttp3.internal.http2.Http2Reader
import org.webrtc.EglBase
import org.webrtc.MediaStream
import org.webrtc.SurfaceViewRenderer
import org.webrtc.VideoTrack

fun AppCompatActivity.setRemoteMediaStream(
    stream: MediaStream,
    remoteParticipant: RemoteParticipant){

    val videoTrack :VideoTrack = stream.videoTracks.first()
    videoTrack.addSink(remoteParticipant.getVideoView())

    runOnUiThread{
        remoteParticipant.getVideoView()?.visibility = View.VISIBLE
    }
}


fun AppCompatActivity.createRemoteParticipantVideo(
    remoteParticipant : RemoteParticipant,
    videoViewRes: String,
    textViewRes: String) {
    val mainHandler = Handler(this.mainLooper)
    val myRunnable = Runnable {
        val videoViewId = baseContext.resources.getIdentifier(videoViewRes,"id",baseContext.packageName)
        val videoView = findViewById<SurfaceViewRenderer>(videoViewId)
        remoteParticipant.setVideoView(videoView)
        videoView.setMirror(false)
        val rootEglBase = EglBase.create()
        videoView.init(rootEglBase.eglBaseContext, null)
        videoView.setZOrderMediaOverlay(true)

        val textViewId = baseContext.resources.getIdentifier(textViewRes,"id",baseContext.packageName)
        val textView = findViewById<TextView>(textViewId)
        remoteParticipant.setParticipantNameText(textView)
        remoteParticipant.getParticipantNameText().text = remoteParticipant.getParticipantName()
        remoteParticipant.getParticipantNameText().setPadding(20,3,20,3)
    }
    mainHandler.post(myRunnable)

}
