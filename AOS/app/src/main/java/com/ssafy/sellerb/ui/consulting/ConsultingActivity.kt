package com.ssafy.sellerb.ui.consulting

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ssafy.sellerb.databinding.ActivityConsultingBinding
import com.ssafy.sellerb.util.Constants.OPENVIDU_SECRET
import com.ssafy.sellerb.util.Constants.OPENVIDU_URL
import com.ssafy.webrtc.openvidu.LocalParticipant
import com.ssafy.webrtc.openvidu.RemoteParticipant
import com.ssafy.webrtc.openvidu.Session
import com.ssafy.webrtc.utils.CustomHttpClient
import com.ssafy.webrtc.websocket.CustomWebSocket
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import org.webrtc.EglBase
import java.io.IOException

class ConsultingActivity : AppCompatActivity() {

    lateinit var binding: ActivityConsultingBinding

    private lateinit var session : Session
    private lateinit var httpClient: CustomHttpClient


    companion object{
        const val TAG = "ConsultingActivity"
        private const val MY_PERMISSIONS_REQUEST_CAMERA = 100;
        private const val MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
        private const val MY_PERMISSIONS_REQUEST = 102;

        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS
            ).toTypedArray()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityConsultingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)


        if (allPermissionsGranted()) {
            initViews()
            httpClient = CustomHttpClient(
                OPENVIDU_URL, "Basic " + android.util.Base64.encodeToString(
                    "OPENVIDUAPP:$OPENVIDU_SECRET".toByteArray(),android.util.Base64.DEFAULT
                ).trim())

            val sessionId = "TEST"
            getToken(sessionId)

        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }
    private fun getToken(sessionId: String) {
        try {
            // Session Request
            val sessionBody: RequestBody = RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                "{\"customSessionId\": \"$sessionId\"}"
            )
            httpClient.httpCall(
                "/openvidu/api/sessions",
                "POST",
                "application/json",
                sessionBody,
                object : Callback {
                    @Throws(IOException::class)
                    override fun onResponse(call: Call, response: Response) {
                        Log.d(TAG, "responseString: " + response.body!!.string())

                        // Token Request
                        val tokenBody: RequestBody =
                            RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), "{}")
                        httpClient.httpCall(
                            "/openvidu/api/sessions/$sessionId/connection",
                            "POST",
                            "application/json",
                            tokenBody,
                            object : Callback {
                                override fun onResponse(call: Call, response: Response) {
                                    var responseString: String? = null
                                    try {
                                        responseString = response.body!!.string()
                                    } catch (e: IOException) {
                                        Log.e(TAG, "Error getting body", e)
                                    }
                                    Log.d(TAG, "responseString2: $responseString")
                                    var tokenJsonObject: JSONObject? = null
                                    var token: String? = null
                                    try {
                                        tokenJsonObject = JSONObject(responseString)
                                        token = tokenJsonObject.getString("token")
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                                    getTokenSuccess(token!!, sessionId)
                                }

                                override fun onFailure(call: Call, e: IOException) {
                                    Log.e(TAG, "Error POST /api/tokens", e)
                                }
                            })
                    }
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(TAG, "Error POST /api/sessions", e)
                    }
                })
        } catch (e: IOException) {
            Log.e(TAG, "Error getting token", e)
            e.printStackTrace()
        }
    }
    private fun initViews(){
        val rootEgleBase = EglBase.create()
        binding.localGlSurfaceView.init(rootEgleBase.eglBaseContext, null)
        binding.localGlSurfaceView.setMirror(true)
        binding.localGlSurfaceView.setEnableHardwareScaler(true)
        binding.localGlSurfaceView.setZOrderMediaOverlay(true)
    }
    private fun getTokenSuccess(token: String, sessionId: String) {
        // Initialize our session
        session = Session(sessionId, token, this)

        // Initialize our local participant and start local camera
        val participantName: String = "TEST"
        val localParticipant =
            LocalParticipant(participantName, session, this.applicationContext, binding.localGlSurfaceView)
        localParticipant.startCamera()

        // Initialize and connect the websocket to OpenVidu Server
        startWebSocket()
    }
    fun viewToDisconnectedState(){
        runOnUiThread{
            binding.localGlSurfaceView.clearImage()
            binding.localGlSurfaceView.release()
        }
    }

    private fun startWebSocket(){
        val webSocket = CustomWebSocket(session, OPENVIDU_URL, this)
        webSocket.execute()
        session.setWebSocket(webSocket)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                //시작
            } else {
                Toast.makeText(this,
                    "권한 설정을 확인해주세요.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun createRemoteParticipantVideo(remoteParticipant: RemoteParticipant){
        val mainHandler = Handler(mainLooper)
        val myRunnable = Runnable {
            remoteParticipant.setVideoView(binding.remoteGlSurfaceView)
            binding.remoteGlSurfaceView.setMirror(false)
            val rootEglBase = EglBase.create()
            binding.remoteGlSurfaceView.init(rootEglBase.eglBaseContext, null)
            binding.remoteGlSurfaceView.setZOrderMediaOverlay(true)
            remoteParticipant.setParticipantNameText((binding.remoteParticipant as TextView)!!)
            remoteParticipant.getParticipantNameText().text = remoteParticipant.getParticipantName()
            remoteParticipant.getParticipantNameText().setPadding(20, 3, 20, 3)
        }
        mainHandler.post(myRunnable)
    }

    fun leaveSession() {
        this.session.leaveSession()
        this.httpClient.dispose()
    }

    override fun onDestroy() {
        leaveSession()
        super.onDestroy()
    }

    override fun onBackPressed() {
        leaveSession()
        super.onBackPressed()
    }

    override fun onStop() {
        leaveSession()
        super.onStop()
    }
}