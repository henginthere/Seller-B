package com.ssafy.sellerb.ui.consulting

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ssafy.sellerb.data.remote.response.ConsultingStateResponse
import com.ssafy.sellerb.databinding.ActivityConsultingBinding
import com.ssafy.sellerb.util.Constants.EXTRA_KEY_CONSULTING_INFO
import com.ssafy.sellerb.util.Constants.OPENVIDU_SECRET
import com.ssafy.sellerb.util.Constants.OPENVIDU_URL
import com.ssafy.webrtc.openvidu.LocalParticipant
import com.ssafy.webrtc.openvidu.Session
import com.ssafy.webrtc.utils.CustomHttpClient
import com.ssafy.webrtc.websocket.CustomWebSocket
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import org.webrtc.EglBase
import java.io.IOException
import java.text.DecimalFormat

class ConsultingActivity : AppCompatActivity() {

    lateinit var binding: ActivityConsultingBinding

    private lateinit var session: Session
    private lateinit var httpClient: CustomHttpClient
    private var toggle = true
    private lateinit var consultingInfo: ConsultingStateResponse
    private lateinit var customerId: String
    private lateinit var customerName: String
    private lateinit var audioManager: AudioManager
    private var isSpeaker = false

    companion object {
        const val TAG = "ConsultingActivity"

        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
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

        consultingInfo = intent.getSerializableExtra(EXTRA_KEY_CONSULTING_INFO)
                as ConsultingStateResponse

        customerId = intent.getStringExtra("customerId").toString()
        customerName = intent.getStringExtra("customerName").toString()

        if (consultingInfo != null) {
            val consultantName = binding.tvConsultantName.text.toString()
            val productPrice = binding.tvProductPrice.text.toString()
            val productName = binding.tvProductName.text.toString()
            binding.tvConsultantName.text =
                consultantName + consultingInfo.consultant.consultantName
            binding.tvProductName.text =
                productName + consultingInfo.product.name
            val dec = DecimalFormat("#,###")
            binding.tvProductPrice.text =
                productPrice + dec.format(consultingInfo.product.price) + "원"
        }
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.mode = AudioManager.MODE_NORMAL

        binding.btnSwitchCamera.setOnClickListener {
            session.getLocalParticipant()!!.switchCamera()
        }

        binding.btnExit.setOnClickListener {
            leaveSession()
            finish()
        }

        binding.viewsContainer.setOnClickListener {
            resizeView()
        }
        binding.btnSpeakerMode.isActivated = false

        binding.btnSpeakerMode.setOnClickListener {
            it.isActivated = !it.isActivated
            audioManager.isSpeakerphoneOn = !audioManager.isSpeakerphoneOn
        }
    }

    private fun resizeView() {
        var width: Int
        var height: Int

        if (toggle) {
            width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                90f, resources.displayMetrics
            ).toInt()
            height = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                120f, resources.displayMetrics
            ).toInt()

        } else {
            width = RelativeLayout.LayoutParams.MATCH_PARENT
            height = RelativeLayout.LayoutParams.MATCH_PARENT
        }
        binding.peerContainerRemote.layoutParams = RelativeLayout.LayoutParams(width, height)

        toggle = !toggle
    }

    override fun onResume() {
        super.onResume()

        if (allPermissionsGranted()) {
            initViews()
            httpClient = CustomHttpClient(
                OPENVIDU_URL, "Basic " + android.util.Base64.encodeToString(
                    "OPENVIDUAPP:$OPENVIDU_SECRET".toByteArray(), android.util.Base64.DEFAULT
                ).trim()
            )

            val sessionId = customerId + "-session"
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
                            RequestBody.create(
                                "application/json; charset=utf-8".toMediaTypeOrNull(),
                                "{}"
                            )
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
                                    viewToDisconnectedState()
                                }
                            })
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(TAG, "Error POST /api/sessions", e)
                        viewToDisconnectedState()
                    }
                })
        } catch (e: IOException) {
            Log.e(TAG, "Error getting token", e)
            e.printStackTrace()
            viewToDisconnectedState()
        }
    }

    private fun initViews() {
        val rootEgleBase = EglBase.create()
        binding.localGlSurfaceView.init(rootEgleBase.eglBaseContext, null)
        binding.localGlSurfaceView.setMirror(true)
        binding.localGlSurfaceView.setEnableHardwareScaler(true)
        binding.localGlSurfaceView.setZOrderMediaOverlay(true)
    }

    private fun getTokenSuccess(token: String, sessionId: String) {
        // Initialize our session
        session = Session(sessionId, token, this, binding.viewsContainer)

        // Initialize our local participant and start local camera
        val participantName: String = customerName
        val localParticipant =
            LocalParticipant(
                participantName,
                session,
                this.applicationContext,
                binding.localGlSurfaceView
            )
        localParticipant.startCamera()

        // Initialize and connect the websocket to OpenVidu Server
        startWebSocket()
    }

    fun viewToDisconnectedState() {
        runOnUiThread {
            binding.localGlSurfaceView.clearImage()
            binding.localGlSurfaceView.release()
//            binding.remoteGlSurfaceView.clearImage()
//            binding.remoteGlSurfaceView.release()
        }
    }


    private fun startWebSocket() {
        val webSocket = CustomWebSocket(session, OPENVIDU_URL, this)
        webSocket.execute()
        session.setWebSocket(webSocket)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                //시작
            } else {
                Toast.makeText(
                    this,
                    "권한 설정을 확인해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun leaveSession() {
        this.session.leaveSession()
        this.httpClient.dispose()
        runOnUiThread {
            binding.localGlSurfaceView.clearImage()
            binding.localGlSurfaceView.release()
//            binding.remoteGlSurfaceView.clearImage()
//            binding.remoteGlSurfaceView.release()
        }
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
    }

    override fun onPause() {
        leaveSession()
        super.onPause()
    }

}