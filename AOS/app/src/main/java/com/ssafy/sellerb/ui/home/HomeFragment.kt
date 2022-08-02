package com.ssafy.sellerb.ui.home


import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentHomeBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel>(){

    companion object {
        const val TAG = "HomeFragment"
    }

    lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentHomeBinding.bind(view)

        linearLayoutManager = LinearLayoutManager(requireContext())

    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.isLogin.observe(this){
            it.getIfNotHandled()?.run{
                if(!this){
                    findNavController().navigate(R.id.action_homeFragment_to_myPageFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}