package com.ssafy.sellerb.ui.consulting.review

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.DialogConsultingReviewBinding
import com.ssafy.sellerb.di.component.DialogFragmentComponent
import com.ssafy.sellerb.ui.base.BaseDialog

class ConsultingReviewDialog : BaseDialog<ConsultingReviewDialogViewModel>() {

    companion object {
        fun newInstance(): ConsultingReviewDialog{
            val args = Bundle()
            val fragment = ConsultingReviewDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: DialogConsultingReviewBinding? = null
    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.dialog_consulting_review

    override fun injectDependencies(dialogComponent: DialogFragmentComponent) =
        dialogComponent.inject(this)

    override fun setupView(view: View) {
        _binding = DialogConsultingReviewBinding.bind(view)

        binding.btnReviewCancel.setOnClickListener {
            findNavController().navigate(R.id.action_ConsultingReviewDialog_to_HomeFragment)
        }

        binding.btnReviewRegister.setOnClickListener {
            findNavController().navigate(R.id.action_ConsultingReviewDialog_to_HomeFragment)
        }
    }

}