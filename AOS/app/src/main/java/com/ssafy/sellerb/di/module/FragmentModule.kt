package com.ssafy.sellerb.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.ViewModelProviderFactory
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.home.HomeViewModel
import com.ssafy.sellerb.ui.login.LoginViewModel
import com.ssafy.sellerb.ui.mypage.MyPageViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        userRepository: UserRepository
    ): HomeViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(HomeViewModel::class){
            HomeViewModel(coroutineDispatchers, userRepository)
        })[HomeViewModel::class.java]

    @Provides
    fun provideMyPageViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        userRepository: UserRepository
    ): MyPageViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(MyPageViewModel::class){
            MyPageViewModel(coroutineDispatchers,userRepository)
        })[MyPageViewModel::class.java]

    @Provides
    fun provideLoginViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(LoginViewModel::class){
            LoginViewModel(coroutineDispatchers,userRepository)
        })[LoginViewModel::class.java]

}