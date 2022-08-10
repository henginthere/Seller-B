package com.ssafy.webrtc.openvidu

import android.content.Context
import android.os.Build
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
        getSession().setLocalParticipant(this)
    }

    fun startCamera(){
        val eglBaseContext = EglBase.create().eglBaseContext
        val peerConnectionFactory = getSession().getPeerConnectionFactory()!!

        val audioSource = peerConnectionFactory.createAudioSource(MediaConstraints())
        setAudioTrack(peerConnectionFactory.createAudioTrack("101", audioSource))

        surfaceTextureHelper = SurfaceTextureHelper.create(
            "CaptureThread", eglBaseContext
        )
        val videoCapturer = createCameraCapturer()
        val videoSource = peerConnectionFactory.createVideoSource(videoCapturer!!.isScreencast)
        videoCapturer.initialize(surfaceTextureHelper, context, videoSource.capturerObserver)
        videoCapturer.startCapture(480, 640, 30)

        setVideoTrack(peerConnectionFactory.createVideoTrack("102", videoSource))
        getVideoTrack().addSink(surfaceViewRenderer)
    }

    fun createCameraCapturer(): VideoCapturer?{
        var enumerator: CameraEnumerator

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            enumerator = Camera2Enumerator(this.context)
        }else{
            enumerator = Camera1Enumerator(false)
        }

        val devicenames = enumerator.deviceNames

        for(i in devicenames.iterator()){
            if(enumerator.isBackFacing(i)){
                videoCapturer = enumerator.createCapturer(i, null)
                if(videoCapturer != null){
                    return videoCapturer
                }
            }
        }

        for(i in devicenames.iterator()){
            if(enumerator.isFrontFacing(i)){
                videoCapturer = enumerator.createCapturer(i, null)
                if(videoCapturer !=null){
                    return videoCapturer
                }
            }
        }
        return null
    }

    fun switchCamera(){
        if(videoCapturer != null){
            if(videoCapturer is CameraVideoCapturer){
                val cameraVideoCapturer = videoCapturer as CameraVideoCapturer
                cameraVideoCapturer.switchCamera(null)
            }
        }
    }

    override fun dispose() {
        super.dispose();
        if(getVideoTrack() != null){
            getVideoTrack().removeSink(surfaceViewRenderer)
            videoCapturer.dispose()
        }
        if(surfaceTextureHelper != null){
            surfaceTextureHelper.dispose()
        }
    }
}