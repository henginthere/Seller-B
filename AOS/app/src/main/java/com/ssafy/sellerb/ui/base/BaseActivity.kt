package com.ssafy.sellerb.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.component.ActivityComponent
import com.ssafy.sellerb.di.component.DaggerActivityComponent
import com.ssafy.sellerb.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(){

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setUpView(savedInstanceState)
        viewModel.onCreate()
    }
    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as SellerBApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected abstract fun provideLayoutId(): View

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setUpView(savedInstanceState: Bundle?)
}