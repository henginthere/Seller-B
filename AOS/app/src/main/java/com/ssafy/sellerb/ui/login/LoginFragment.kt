package com.ssafy.sellerb.ui.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentLoginBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment

class LoginFragment : BaseFragment<LoginViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_login

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

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
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}