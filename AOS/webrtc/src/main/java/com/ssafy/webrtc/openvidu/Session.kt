package com.ssafy.webrtc.openvidu

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.webrtc.observers.CustomPeerConnectionObserver
import com.ssafy.webrtc.observers.CustomSdpObserver
import com.ssafy.webrtc.utils.setRemoteMediaStream
import com.ssafy.webrtc.websocket.CustomWebSocket
import org.webrtc.*
import org.webrtc.PeerConnection.*
import org.webrtc.RtpTransceiver.RtpTransceiverInit

class Session(
    private val id: String,
    private val token: String,
    private var activity: AppCompatActivity?,
    private var viewContainer: RelativeLayout?
) {

    private var peerConnectionFactory: PeerConnectionFactory?
    private val remoteParticipants: MutableMap<String, RemoteParticipant> = mutableMapOf()
    private var localParticipant: LocalParticipant? = null
    private var websocket: CustomWebSocket? = null

    init {
        val optionsBuilder = PeerConnectionFactory
            .InitializationOptions.builder(activity!!.applicationContext)
        optionsBuilder.setEnableInternalTracer(true)

        val opt = optionsBuilder.createInitializationOptions()
        PeerConnectionFactory.initialize(opt)

        val options = PeerConnectionFactory.Options()

        val encoderFactory = SoftwareVideoEncoderFactory()
        val decoderFactory = SoftwareVideoDecoderFactory()

        peerConnectionFactory = PeerConnectionFactory.builder()
            .setVideoEncoderFactory(encoderFactory)
            .setVideoDecoderFactory(decoderFactory)
            .setOptions(options)
            .createPeerConnectionFactory()

    }

    fun createLocalPeerConnection(): PeerConnection?{
        val iceServers = ArrayList<PeerConnection.IceServer>();
        val iceServer = PeerConnection.IceServer
            .builder("stun:stun.l.google.com:19302")
            .createIceServer()
        iceServers.add(iceServer)

        val rtcConfig = PeerConnection.RTCConfiguration(iceServers)
        rtcConfig.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.ENABLED
        rtcConfig.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE
        rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.NEGOTIATE
        rtcConfig.continualGatheringPolicy = PeerConnection
            .ContinualGatheringPolicy
            .GATHER_CONTINUALLY
        rtcConfig.keyType = PeerConnection.KeyType.ECDSA
        rtcConfig.enableDtlsSrtp = true;
        rtcConfig.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN
        if(peerConnectionFactory != null){
            val peerConnection = peerConnectionFactory!!.createPeerConnection(
                rtcConfig, object : CustomPeerConnectionObserver("local"){
                    override fun onIceCandidate(iceCandidate: IceCandidate) {
                        super.onIceCandidate(iceCandidate)
                        websocket?.onIceCandidate(iceCandidate, localParticipant?.getConnectionId())
                    }

                    override fun onSignalingChange(signalingState: SignalingState) {
                        if(PeerConnection.SignalingState.STABLE == signalingState){
                            val it = localParticipant?.getIceCandidateList()?.iterator()
                            while (it!!.hasNext()){
                                val candidate = it.next()
                                localParticipant?.getPeerConnection()?.addIceCandidate(candidate)
                                it.remove()
                            }
                        }
                    }

                })
            if(localParticipant?.getAudioTrack() != null){
                peerConnection!!.addTransceiver(localParticipant?.getAudioTrack(),
                    RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY))
            }
            if(localParticipant?.getVideoTrack() != null){
                peerConnection!!.addTransceiver(localParticipant?.getVideoTrack(),
                    RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY))
            }

            return peerConnection!!
        }
        return null
    }

    fun createRemotePeerConnection(connectionId: String?) {
        val iceServers: MutableList<IceServer> = java.util.ArrayList()
        val iceServer = IceServer.builder("stun:stun.l.google.com:19302").createIceServer()
        iceServers.add(iceServer)
        val rtcConfig = RTCConfiguration(iceServers)
        rtcConfig.tcpCandidatePolicy = TcpCandidatePolicy.ENABLED
        rtcConfig.bundlePolicy = BundlePolicy.MAXBUNDLE
        rtcConfig.rtcpMuxPolicy = RtcpMuxPolicy.NEGOTIATE
        rtcConfig.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_CONTINUALLY
        rtcConfig.keyType = KeyType.ECDSA
        rtcConfig.enableDtlsSrtp = true
        rtcConfig.sdpSemantics = SdpSemantics.UNIFIED_PLAN
        if(peerConnectionFactory != null){
            val peerConnection = peerConnectionFactory!!.createPeerConnection(
                rtcConfig,
                object : CustomPeerConnectionObserver("remotePeerCreation") {
                    override fun onIceCandidate(iceCandidate: IceCandidate) {
                        super.onIceCandidate(iceCandidate)
                        websocket?.onIceCandidate(iceCandidate, connectionId)
                    }

                    override fun onAddTrack(
                        rtpReceiver: RtpReceiver?,
                        mediaStreams: Array<MediaStream?>?
                    ) {
                        super.onAddTrack(rtpReceiver, mediaStreams)
                        if(mediaStreams != null && mediaStreams[0] != null && remoteParticipants != null){
                            activity!!.setRemoteMediaStream(
                                mediaStreams[0]!!,
                                remoteParticipants[connectionId]!!)
                        }
                    }

                    override fun onSignalingChange(signalingState: SignalingState) {
                        if (SignalingState.STABLE == signalingState) {
                            // SDP Offer/Answer finished. Add stored remote candidates.
                            val remoteParticipant = remoteParticipants[connectionId]!!
                            val it: MutableIterator<IceCandidate> =
                                remoteParticipant.getIceCandidateList().iterator()
                            while (it.hasNext()) {
                                val candidate = it.next()
                                remoteParticipant.getPeerConnection().addIceCandidate(candidate)
                                it.remove()
                            }
                        }
                    }
                })
            peerConnection!!.addTransceiver(
                MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO,
                RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.RECV_ONLY)
            )
            peerConnection.addTransceiver(
                MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO,
                RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.RECV_ONLY)
            )
            remoteParticipants[connectionId]!!.setPeerConnection(peerConnection)
        }
    }

    fun createOfferForPublishing(constraints: MediaConstraints?) {
        localParticipant?.getPeerConnection()?.createOffer(object : CustomSdpObserver("createOffer") {
            override fun onCreateSuccess(sessionDescription: SessionDescription?) {
                super.onCreateSuccess(sessionDescription)
                Log.i("createOffer SUCCESS", sessionDescription.toString())
                localParticipant?.getPeerConnection()?.setLocalDescription(
                    CustomSdpObserver("createOffer_setLocalDescription"),
                    sessionDescription
                )
                websocket?.publishVideo(sessionDescription!!)
            }

            override fun onCreateFailure(s: String?) {
                Log.e("createOffer ERROR", s!!)
            }
        }, constraints)
    }

    fun createAnswerForSubscribing(
        remoteParticipant: RemoteParticipant,
        streamId: String?,
        constraints: MediaConstraints?
    ) {
        remoteParticipant.getPeerConnection()
            .createAnswer(object : CustomSdpObserver("createAnswerSubscribing") {
                override fun onCreateSuccess(sessionDescription: SessionDescription?) {
                    super.onCreateSuccess(sessionDescription)
                    Log.i("createAnswer SUCCESS", sessionDescription.toString())
                    remoteParticipant.getPeerConnection().setLocalDescription(object :
                        CustomSdpObserver("createAnswerSubscribing_setLocalDescription") {
                        override fun onSetSuccess() {
                            websocket?.receiveVideoFrom(
                                sessionDescription,
                                remoteParticipant,
                                streamId!!
                            )
                        }

                        override fun onSetFailure(s: String?) {
                            Log.e("setRemoteDescription ER", s!!)
                        }
                    }, sessionDescription)
                }

                override fun onCreateFailure(s: String?) {
                    Log.e("createAnswer ERROR", s!!)
                }
            }, constraints)
    }

    fun getId(): String? {
        return id
    }

    fun getToken(): String? {
        return token
    }

    fun getLocalParticipant(): LocalParticipant? {
        return localParticipant
    }

    fun setLocalParticipant(localParticipant: LocalParticipant?) {
        this.localParticipant = localParticipant!!
    }

    fun getRemoteParticipant(id: String?): RemoteParticipant? {
        return remoteParticipants[id]
    }

    fun getRemoteNum() : Int = remoteParticipants.size

    fun getPeerConnectionFactory(): PeerConnectionFactory? {
        return peerConnectionFactory
    }

    fun addRemoteParticipant(remoteParticipant: RemoteParticipant) {
        remoteParticipants.put(remoteParticipant.getConnectionId(), remoteParticipant)
    }

    fun removeRemoteParticipant(id: String?): RemoteParticipant? {
        return remoteParticipants.remove(id)
    }

    fun leaveSession() {
        AsyncTask.execute {
            websocket?.setWebsocketCancelled(true)
            websocket?.leaveRoom()
            websocket?.disconnect()
            websocket = null
            localParticipant?.dispose()
            localParticipant = null
        }
        activity!!.runOnUiThread {
            for (remoteParticipant in remoteParticipants.values) {
                if (remoteParticipant.getPeerConnection() != null) {
                    remoteParticipant.getPeerConnection().close()
                }
                if(remoteParticipant.getView() != null){
                    viewContainer?.removeView(remoteParticipant.getView())
                }
            }
            viewContainer = null
        }
        AsyncTask.execute {
            if (peerConnectionFactory != null) {
                peerConnectionFactory!!.dispose()
                peerConnectionFactory = null
            }
        }

    }

    fun setWebSocket(websocket: CustomWebSocket){
        this.websocket = websocket
    }

    fun removeView(view: View){
        this.viewContainer?.removeView(view)
    }
}