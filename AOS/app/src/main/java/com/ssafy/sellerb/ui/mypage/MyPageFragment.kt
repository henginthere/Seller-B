package com.ssafy.sellerb.ui.mypage

import android.view.View
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentMyPageBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment


class MyPageFragment : BaseFragment<MyPageViewModel>() {

    companion object{
        const val TAG = "MyPageFragment"
    }

    private var _binding: FragmentMyPageBinding? =null

    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.fragment_my_page

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)


    override fun setupView(view: View) {

        _binding = FragmentMyPageBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}