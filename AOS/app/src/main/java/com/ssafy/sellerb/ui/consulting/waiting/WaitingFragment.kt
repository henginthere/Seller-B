package com.ssafy.sellerb.ui.consulting.waiting

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentWaitingBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.consulting.ConsultingActivity
import com.ssafy.sellerb.ui.main.MainSharedViewModel
import com.ssafy.sellerb.util.Constants.EXTRA_KEY_CONSULTING_INFO
import com.ssafy.sellerb.util.GlideHelper
import javax.inject.Inject

class WaitingFragment : BaseFragment<WaitingViewModel>() {

    companion object {
        const val TAG = "WaitingFragment"
    }

    private var _binding: FragmentWaitingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                findNavController().navigate(R.id.action_WaitingFragment_to_ConsultingReviewDialog)
            }
        }


    override fun provideLayoutId(): Int = R.layout.fragment_waiting

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentWaitingBinding.bind(view)

        binding.btnCancel.setOnClickListener {
            viewModel.doCancel()
        }

        binding.btnTest.setOnClickListener {
            val intent = Intent(context, ConsultingActivity::class.java)
            startActivity(intent)
        }

        val animation = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.rotate)
        binding.ivLogo.animation = animation

    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.waiting.observe(this) {
            Glide
                .with(binding.ivProductThumbnail.context)
                .load(it.productThumbnail)
                .into(binding.ivProductThumbnail)
            binding.tvProductName.text = it.productName
        }

        viewModel.waitingCancel.observe(this) {
            findNavController().navigate(R.id.action_WaitingFragment_to_HomeFragment)
        }

        viewModel.isWaiting.observe(this) {
            if (it) {
                val productSeq = mainSharedViewModel.qrCodeResult.value!!.peek()
                viewModel.startWaiting(productSeq)
            } else {
                viewModel.loadWaiting()
            }
        }

        viewModel.consultingInfo.observe(this) {
            viewModel.setConsulting()
        }

        viewModel.startConsulting.observe(this) {
            if (it) {
                val intent = Intent(requireContext(), ConsultingActivity::class.java)
                intent.putExtra(EXTRA_KEY_CONSULTING_INFO, viewModel.consultingInfo.value)
                startForResult.launch(intent)
            }
        }

    }

}