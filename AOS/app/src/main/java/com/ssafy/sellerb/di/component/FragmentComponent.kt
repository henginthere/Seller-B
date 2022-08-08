package com.ssafy.sellerb.di.component

import com.ssafy.sellerb.FragmentScope
import com.ssafy.sellerb.di.module.FragmentModule
import com.ssafy.sellerb.ui.consulting.WaitingFragment
import com.ssafy.sellerb.ui.home.HomeFragment
import com.ssafy.sellerb.ui.login.LoginFragment
import com.ssafy.sellerb.ui.mypage.MyPageFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: MyPageFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: WaitingFragment)

}