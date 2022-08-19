package com.ssafy.sellerb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.component.DaggerFragmentComponent
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.di.module.FragmentModule
import javax.inject.Inject

 abstract class BaseFragment<VM : BaseViewModel> : Fragment(){

    @Inject
    lateinit var viewModel: VM

     override fun onCreate(savedInstanceState: Bundle?) {
         injectDependencies(buildFragmentComponent())
         super.onCreate(savedInstanceState)
         setUpObserver()
         viewModel.onCreate()
     }

    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as SellerBApplication)
                .applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? = layoutInflater.inflate(provideLayoutId(),container,false)

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         setupView(view)
     }

     protected open fun setUpObserver(){

     }
    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(view: View)
}