package com.ssafy.sellerb.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.ViewModelProviderFactory
import com.ssafy.sellerb.ui.base.BaseActivity
import com.ssafy.sellerb.ui.main.MainActivity
import com.ssafy.sellerb.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class){
            MainViewModel()
        }).get(MainViewModel::class.java)

}