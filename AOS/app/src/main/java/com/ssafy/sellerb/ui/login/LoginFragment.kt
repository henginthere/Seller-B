package com.ssafy.sellerb.ui.login

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentLoginBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.qrscan.QrScanActivity
import com.ssafy.sellerb.util.Constants.CLIENT_ID


class LoginFragment : BaseFragment<LoginViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_login

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    companion object{
        const val RC_SIGN_IN = 1001
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {

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

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(CLIENT_ID)
            .requestServerAuthCode(CLIENT_ID)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(context!!, gso);


        binding.signInButton.setSize(SignInButton.SIZE_ICON_ONLY)
        binding.signInButton.setOnClickListener{
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

        binding.btnLogin.setOnClickListener{
            viewModel.doLogin()
            mGoogleSignInClient.signOut()
        }
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account = completedTask.getResult(ApiException::class.java)
            as GoogleSignInAccount
            val idToken = account.idToken
            Log.e("GOOGLE", idToken!!)
            Toast.makeText(context, "idToken:${idToken}",Toast.LENGTH_SHORT).show()
        }catch (e : ApiException){
            Log.w("LoginFragment", "handleSignInResult:error", e)
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
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
}