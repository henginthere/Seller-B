package com.ssafy.sellerb.ui.consulting.history

import android.view.View
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentConsultingHistoryBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment

class ConsultingHistoryFragment : BaseFragment<ConsultingHistoryViewModel>() {

    private var _binding: FragmentConsultingHistoryBinding? = null
    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.fragment_consulting_history

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentConsultingHistoryBinding.bind(view)


    }
}