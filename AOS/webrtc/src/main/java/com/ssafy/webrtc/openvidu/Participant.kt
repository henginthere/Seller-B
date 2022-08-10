package com.ssafy.webrtc.openvidu

import android.util.Log
import org.webrtc.AudioTrack
import org.webrtc.IceCandidate
import org.webrtc.MediaStream
import org.webrtc.PeerConnection
import org.webrtc.VideoTrack

abstract class Participant(
    private val participantName: String,
    private val session: Session
    ) {

    private lateinit var connectionId: String
    private lateinit var iceCandidateList: List<IceCandidate>
    private lateinit var peerConnection: PeerConnection
    private lateinit var audioTrack: AudioTrack
    private lateinit var videoTrack: VideoTrack
    private lateinit var mediaStream: MediaStream

    fun getParticipantName() = participantName

    fun getSession() = session

    fun setPeerConnection(peerConnection: PeerConnection){
        this.peerConnection = peerConnection
    }

    fun getPeerConnection(): PeerConnection = peerConnection

    fun setConnectionId(connectionId: String){
        this.connectionId = connectionId
    }
    fun getConnectionId(): String = connectionId

    fun setIceCandidateList(iceCandidates: List<IceCandidate>){
        iceCandidateList = iceCandidates
    }

    fun getIceCandidateList(): MutableList<IceCandidate> =
        iceCandidateList as MutableList<IceCandidate>

    fun setAudioTrack(audioTrack: AudioTrack){
        this.audioTrack = audioTrack
    }
    fun getAudioTrack(): AudioTrack{
        return audioTrack
    }

    fun setVideoTrack(videoTrack: VideoTrack){
        this.videoTrack = videoTrack
    }

    fun getVideoTrack(): VideoTrack{
        return videoTrack
    }
    constructor(connectionId: String, participantName: String, session: Session)
            : this(participantName,session){
                this.connectionId = connectionId
    }

    open fun dispose() {
        if(peerConnection != null){
           try{
                peerConnection.close()
           }catch(e : IllegalStateException){
               Log.e("Dispose PeerConnection" , e.message.toString())
           }
        }
    }


}