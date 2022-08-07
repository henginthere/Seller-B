package com.ssafy.sellerb.ui.home


import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentHomeBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.qrscan.QrScanActivity

class HomeFragment : BaseFragment<HomeViewModel>(){

    companion object {
        const val TAG = "HomeFragment"
    }

    lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val intent = it.data
            if(intent != null){
                val url = intent.getStringExtra("url")
                Toast.makeText(context,"Value: " + url, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_home


    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentHomeBinding.bind(view)

        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.ibIconQrScan.setOnClickListener{
            val intent = Intent(requireContext(), QrScanActivity::class.java)
            startForResult.launch(intent)
        }
    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.isLogin.observe(this){
            it.getIfNotHandled()?.run{
                if(!this){
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}