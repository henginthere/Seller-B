package com.ssafy.sellerb.ui.consulting

import android.view.View
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentHomeBinding
import com.ssafy.sellerb.databinding.FragmentWaitingBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.main.MainSharedViewModel
import javax.inject.Inject

class WaitingFragment : BaseFragment<WaitingViewModel>(){

    companion object {
        const val TAG = "WaitingFragment"
    }

    private var _binding: FragmentWaitingBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun provideLayoutId(): Int = R.layout.fragment_waiting

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentWaitingBinding.bind(view)

        binding.tvQrTest.text = mainSharedViewModel.qrCodeUrl.value!!.peek()
    }

    override fun setUpObserver() {
        super.setUpObserver()

    }

}