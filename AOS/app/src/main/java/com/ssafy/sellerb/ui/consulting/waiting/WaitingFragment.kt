package com.ssafy.sellerb.ui.consulting.waiting

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentWaitingBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.consulting.ConsultingActivity
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

        binding.btnCancel.setOnClickListener{
            findNavController().navigate(R.id.action_WaitingFragment_to_HomeFragment)
        }

        binding.btnTest.setOnClickListener {
            val intent = Intent(context, ConsultingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.waitingSeq.observe(this){
            Toast.makeText(context, "상담 대기중.. 잠시만 기다려 주세요 seq: ${it}",
                Toast.LENGTH_SHORT)
                .show()
        }


    }

}