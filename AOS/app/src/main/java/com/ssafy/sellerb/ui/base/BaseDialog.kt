package com.ssafy.sellerb.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.ssafy.sellerb.R
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.component.DaggerDialogFragmentComponent
import com.ssafy.sellerb.di.component.DialogFragmentComponent
import com.ssafy.sellerb.di.module.DialogFragmentModule
import javax.inject.Inject

abstract class BaseDialog<VM : BaseViewModel> : DialogFragment(){

    @Inject
    lateinit var viewModel: VM

    private var baseActivity : BaseActivity<BaseViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity<*>){
            baseActivity = context as BaseActivity<BaseViewModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_consulting_review, container, false)
    }

    private fun buildFragmentComponent() =
        DaggerDialogFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as SellerBApplication)
                .applicationComponent)
            .dialogFragmentModule(DialogFragmentModule(this))
            .build()

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    open fun dismissDialog(tag: String?) {
        dismiss()
    }


    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(dialogComponent: DialogFragmentComponent)

    protected abstract fun setupView(view: View)
}