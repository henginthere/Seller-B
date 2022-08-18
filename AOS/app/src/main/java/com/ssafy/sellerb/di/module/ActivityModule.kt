package com.ssafy.sellerb.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.ViewModelProviderFactory
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseActivity
import com.ssafy.sellerb.ui.main.MainSharedViewModel
import com.ssafy.sellerb.ui.main.MainViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class){
            MainViewModel(coroutineDispatchers, networkHelper, userRepository)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper
    ) : MainSharedViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainSharedViewModel::class){
            MainSharedViewModel(coroutineDispatchers, networkHelper)
        })[MainSharedViewModel::class.java]

}