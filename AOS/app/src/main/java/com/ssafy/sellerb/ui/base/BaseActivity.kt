package com.ssafy.sellerb.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.sellerb.di.component.ActivityComponent
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(){

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        injectDependencies(buildA)
    }
//    private fun buildActivityComponent() =
//        DaggerActivityComponent
//
    protected abstract fun provideLayoutId(): View

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setUpView(savedInstanceState: Bundle?)
}