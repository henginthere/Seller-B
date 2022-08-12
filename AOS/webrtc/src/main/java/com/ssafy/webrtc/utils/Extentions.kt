package com.ssafy.webrtc.utils

import android.annotation.SuppressLint
import android.os.Handler
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
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
    containerViewRes: String) {
    val mainHandler = Handler(this.mainLooper)
    val myRunnable = Runnable {
        val videoViewId = baseContext.resources.getIdentifier(videoViewRes,"id",baseContext.packageName)
        val videoView = findViewById<SurfaceViewRenderer>(videoViewId)
        remoteParticipant.setVideoView(videoView)
        videoView.setMirror(false)
        val rootEglBase = EglBase.create()
        videoView.init(rootEglBase.eglBaseContext, null)
        videoView.setZOrderMediaOverlay(true)
//        val containerViewId = baseContext.resources.getIdentifier(containerViewRes,"id",baseContext.packageName)
//        val containerView = findViewById<FrameLayout>(containerViewId)
//        remoteParticipant.setView(containerView)
    }
    mainHandler.post(myRunnable)
}



fun AppCompatActivity.createRemoteParticipantVideo2(
    remoteParticipant : RemoteParticipant,
    containerRes: String,
    pearViewRes: String,
    ) {
    val mainHandler = Handler(this.mainLooper)
    val myRunnable = Runnable {
        val containerViewId = baseContext.resources.getIdentifier(containerRes,"id",baseContext.packageName)
        val containerView = findViewById<RelativeLayout>(containerViewId)
        val pearViewId = baseContext.resources.getIdentifier(pearViewRes,"layout",baseContext.packageName)
        val rowView = this.layoutInflater.inflate(pearViewId, null)
        val rp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        rowView.layoutParams = rp
        val rowId = View.generateViewId()
        rowView.id = rowId
        containerView.addView(rowView)
        val videoView = (rowView as ViewGroup).getChildAt(0) as SurfaceViewRenderer
        remoteParticipant.setVideoView(videoView)
        videoView.setMirror(false)
        val rootEglBase = EglBase.create()
        videoView.init(rootEglBase.eglBaseContext, null)
        //videoView.setZOrderMediaOverlay(true)
        remoteParticipant.setView(rowView)
    }
    mainHandler.post(myRunnable)
}

fun AppCompatActivity.resizeView(
    toggle: Boolean,
    containerViewRes: String
){
    val mainHandler = Handler(this.mainLooper)
    val myRunnable = Runnable {
        val containerViewId = baseContext.resources.getIdentifier(containerViewRes,"id",baseContext.packageName)
        val containerView = findViewById<FrameLayout>(containerViewId)
        var width: Int
        var height: Int

        if(toggle){
            width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90f, resources.displayMetrics).toInt()
            height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, resources.displayMetrics).toInt()

        }else{
            width = RelativeLayout.LayoutParams.MATCH_PARENT
            height =  RelativeLayout.LayoutParams.MATCH_PARENT
        }
        containerView.layoutParams = RelativeLayout.LayoutParams(width,height)
        containerView.translationZ = 90f
    }
    mainHandler.post(myRunnable)
}