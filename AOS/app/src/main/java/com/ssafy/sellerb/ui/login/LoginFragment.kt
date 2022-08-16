package com.ssafy.sellerb.ui.login

import android.content.Intent
import android.content.IntentSender
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.view.NidOAuthLoginButton
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentLoginBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.util.Constants.CLIENT_ID
import com.ssafy.sellerb.util.Constants.NAVER_CLIENT_ID
import com.ssafy.sellerb.util.Constants.NAVER_CLINET_NAME
import com.ssafy.sellerb.util.Constants.NAVER_CLINET_SECRET

class LoginFragment : BaseFragment<LoginViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_login

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    companion object{
        const val GOOGLE_LOGIN_REQ = 1001
        const val TAG = "LoginFragment"
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {

        auth = Firebase.auth
        NaverIdLoginSDK.initialize(context!!, NAVER_CLIENT_ID, NAVER_CLINET_SECRET, NAVER_CLINET_NAME)


        _binding = FragmentLoginBinding.bind(view)

        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.onIdChange(p0.toString())
            }

        })

        binding.etPwd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.onPasswordChange(p0.toString())
            }

        })

        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }

        oneTapClient = Identity.getSignInClient(context!!)

        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            .setAutoSelectEnabled(true)
            .build()
        binding.signInButton.setSize(SignInButton.SIZE_WIDE)
        binding.signInButton.setOnClickListener{
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(activity!!) { result ->
                    try {
                        startIntentSenderForResult(
                            result.pendingIntent.intentSender, GOOGLE_LOGIN_REQ,
                            null, 0, 0, 0, null)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(activity!!) { e ->
                    Log.d(TAG, e.localizedMessage)
                }
        }

        binding.btnLogin.setOnClickListener{
            viewModel.doLogin()
        }

    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.launchMain.observe(this){
            it.getIfNotHandled()?.run {
                findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
            }
        }

        viewModel.idField.observe(this){
            if(binding.etEmail.text.toString() != it){
                binding.etEmail.setText(it.toString())
            }
        }

        viewModel.pwField.observe(this){
            if(binding.etPwd.text.toString() != it){
                binding.etPwd.setText(it.toString())
            }
        }

        viewModel.loginFail.observe(this){
            it.getIfNotHandled()?.run{
                if(it.peek()){
                    Toast.makeText(context, "로그인 실패! 아이디 및 비밀번호를 확인해 주세요.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GOOGLE_LOGIN_REQ -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken

                    when {
                        idToken != null -> {
                            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(activity!!) { task ->
                                    if (task.isSuccessful) {
                                        val user = auth.currentUser
                                        viewModel.doGoogleLogin(user!!.email!!, user.uid, user.displayName!!)
                                        Log.d(TAG, "signInWithCredential:success")
                                    } else {
                                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                                    }
                                }
                            Log.d(TAG, "Got ID token.")
                        }
                        else -> {
                            Log.d(TAG, "No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {

                }
            }

        }
    }
}