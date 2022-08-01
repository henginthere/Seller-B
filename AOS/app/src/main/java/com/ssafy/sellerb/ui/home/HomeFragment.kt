package com.ssafy.sellerb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.sellerb.R

class HomeFragment : Fragment() {
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    val homeViewModel : HomeViewModel by viewModels {
//        viewModelFactory
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}