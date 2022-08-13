package com.ssafy.sellerb.ui.signup

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentSignupBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment

class SignupFragment : BaseFragment<SignupViewModel>(){

    companion object {
        const val TAG = "SignupFragment"
    }

    private var _binding: FragmentSignupBinding? =null

    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.fragment_signup

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentSignupBinding.bind(view)

        binding.etId.addTextChangedListener(object : TextWatcher{
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

        binding.etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.onNameChange(p0.toString())
            }
        })

        binding.btnSignup.setOnClickListener{
            viewModel.doSignup()
        }
    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.launchLogin.observe(this){
            it.getIfNotHandled()?.run{
                findNavController().navigate(R.id.action_SignupFragment_to_LoginFragment)
            }
        }

        viewModel.idField.observe(this){
            if(binding.etId.text.toString() != it){
                binding.etId
                    .setText(it.toString())
            }
        }

        viewModel.pwField.observe(this){
            if(binding.etPwd.text.toString() != it){
                binding.etPwd.setText(it.toString())
            }
        }

        viewModel.nameField.observe(this){
            if(binding.etName.text.toString() != it){
                binding.etName.setText(it.toString())
            }
        }

    }
}