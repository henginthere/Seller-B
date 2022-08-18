package com.ssafy.sellerb.ui.consulting.history

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentConsultingHistoryBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment

class ConsultingHistoryFragment : BaseFragment<ConsultingHistoryViewModel>() , OnClickListener {

    lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: FragmentConsultingHistoryBinding? = null
    private val binding get() = _binding!!

    lateinit var consultingAdapter: ConsultingAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_consulting_history

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentConsultingHistoryBinding.bind(view)

        linearLayoutManager = LinearLayoutManager(requireContext())

        consultingAdapter = ConsultingAdapter(requireActivity().lifecycle, ArrayList())

        consultingAdapter.setupOnClickListener(this)

        binding.rvConsulting.apply {
            layoutManager = linearLayoutManager
            adapter = consultingAdapter
        }
    }

    override fun onClickConsulting() {
    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.consultings.observe(this){
            if(it.isNotEmpty()){
                consultingAdapter.appendData(it.toMutableList())
            }
        }
    }
}